package Repository;
import ProgramState.ProgramState;
import Exception.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Repository implements IRepository{
    List<ProgramState> statements;
    int currentProgramState;
    private String logFilePath;

    @Override
    public String get_log_file_path(){
        return logFilePath;
    }

    public Repository(String file_path) {
        this.statements = new ArrayList<ProgramState>();
        currentProgramState = 1;
        this.logFilePath = file_path;
        this.clear_file();
    }

    @Override
    public void clear_file()  {
        this.logFilePath = new File(logFilePath).getAbsolutePath();
        try {
            new FileOutputStream(this.logFilePath).close();
        } catch (IOException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Override
    public ProgramState getByID(int id){
        for(ProgramState program: statements){
            if(program.getId() == id)
                return program;
        }
        return null;
    }

    @Override
    public void setProgramStates(List<ProgramState> list){
        statements = list;
    }

    @Override
    public List<ProgramState> get_all(){
        return this.statements;
    }

    @Override
    public void add(ProgramState program){
        this.statements.add(program);
    }

    public void logProgramStatementExecution(ProgramState state) throws MyException {
        try {
            var logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
            logFile.append(state.toString());
            logFile.close();
        } catch (IOException exception) {
            throw new RunTimeException(exception.getMessage());
        }
    }
}
