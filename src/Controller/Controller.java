package Controller;
import ProgramState.ProgramState;
import Repository.IRepository;
import java.io.IOException;
import java.util.*;
import Exception.*;
import Value.*;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Controller {
    ExecutorService executor;
    IRepository repository;
    public ArrayList<ProgramState> completedPrograms;

    public Controller(IRepository repo){
        this.repository = repo;
        this.completedPrograms = new ArrayList<>();
    }

    public List<ProgramState> get_all(){
        return repository.get_all();
    }

    public java.util.List<ProgramState> getCompletedPrograms(java.util.List<ProgramState> inProgramList) {
        return inProgramList.stream()
                .filter(Predicate.not(ProgramState::is_not_completed))
                .collect(Collectors.toList());

    }

    public List<ProgramState> remove_completed_programs(List<ProgramState> inPrgList){
        return inPrgList.stream()
                .filter(p -> p.is_not_completed())
                .collect(Collectors.toList());
    }

    public void oneStepForAllPrograms(List<ProgramState> programStateList) throws RunTimeException {
        programStateList.forEach(program ->
        {
            try {
                repository.logProgramStatementExecution(program);
            } catch (MyException e) {
                e.printStackTrace();
            }
        });

        List<Callable<ProgramState>> callList = programStateList.stream()
                .map((ProgramState p) -> (Callable<ProgramState>) (p::oneStepExecution))
                .collect(Collectors.toList());

        List<ProgramState> newProgramStateList;

        try {
            newProgramStateList = executor.invokeAll(callList).stream()
                    .map(future -> {
                        try {
                            return future.get();
                        } catch (Exception exception) {
                            throw new RuntimeException(exception.getMessage());
                        }
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (InterruptedException exception) {
            throw new RunTimeException(exception.getMessage());
        }

        programStateList.addAll(newProgramStateList);
        programStateList.forEach(program ->
        {
            try {
                repository.logProgramStatementExecution(program);
            } catch (MyException e) {
                e.printStackTrace();
            }
        });
        repository.setProgramStates(programStateList);
    }

    public void oneStepExecution() throws MyException {
        executor = Executors.newFixedThreadPool(2);
        List<ProgramState> programList = remove_completed_programs(repository.get_all());

        if (!programList.isEmpty()) {
            ProgramState state = programList.get(0);
            state.get_heap().setContent(
                    garbageCollector(
                            getAddrFromSymTable(
                                    programList.stream().map(programState -> programState.get_symTable().getContent().values()).collect(Collectors.toList()),
                                    state.get_heap().getContent()
                            ),
                            state.get_heap().getContent()
                    )
            );
            oneStepForAllPrograms(programList);
            completedPrograms.addAll(getCompletedPrograms(repository.get_all()));
            programList = remove_completed_programs(repository.get_all());
        }
        executor.shutdownNow();
        repository.setProgramStates(programList);
    }

    public void allStepsExecution() throws MyException {
        executor = Executors.newFixedThreadPool(2);
        List<ProgramState> programList = remove_completed_programs(repository.get_all());

        while (!programList.isEmpty()) {
            ProgramState state = programList.get(0);
            state.get_heap().setContent(
                    garbageCollector(
                            getAddrFromSymTable(
                                    programList.stream().map(programState -> programState.get_symTable().getContent().values()).collect(Collectors.toList()),
                                    state.get_heap().getContent()
                            ),
                            state.get_heap().getContent()
                    )
            );
            oneStepForAllPrograms(programList);
            programList = remove_completed_programs(repository.get_all());
        }
        executor.shutdownNow();
        repository.setProgramStates(programList);
    }

    public List<ProgramState> getAll(){
        return repository.get_all();
    }
    public String getFile(){ return repository.get_log_file_path();}


    public Map<Integer,IValue> unsafeGarbageCollector(List<Integer> symTableAddr, Map<Integer,IValue> heap){
        return heap.entrySet().stream().filter(e->symTableAddr.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    Set<Integer> getAddrFromSymTable(List<Collection<IValue>> symTableValues, Map<Integer, IValue> heap){
        Set<Integer> toReturn = new TreeSet<>();
        symTableValues.forEach(symTable -> symTable.stream()
                .filter(v -> v instanceof ReferenceValue)
                .forEach(v -> {
                    while (v instanceof ReferenceValue) {
                        toReturn.add(((ReferenceValue)v).get_address());
                        v = heap.get(((ReferenceValue)v).get_address());
                    }
                }));

        return toReturn;
    }

    Map<Integer, IValue> garbageCollector(Set<Integer> symTableAddr, Map<Integer, IValue> heap){
        return heap.entrySet().stream()
                .filter(e -> symTableAddr.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
