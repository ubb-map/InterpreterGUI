package Statement;
import Exception.*;
import ADT.*;
import IType.IType;
import ProgramState.ProgramState;
import Value.IValue;
import java.util.AbstractMap;
import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ForkStatement implements IStatement {
    private final IStatement statement;

    public ForkStatement(IStatement st){
        this.statement = st;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        IDictionary<String, IValue> newSymbolTable = new MyDictionary<>();
        newSymbolTable.setContent(
                state.get_symTable().getContent().entrySet().stream()
                        .map( (Map.Entry<String, IValue> entry) -> new AbstractMap.SimpleEntry<>(entry.getKey(), entry.getValue().copy()))
                        .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue)));

        return new ProgramState(new MyStack<>(), newSymbolTable, state.get_out(), state.get_fileTable(), state.get_heap(), statement);
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeTable) throws MyException {
        statement.typeCheck(typeTable);
        return typeTable;
    }

    @Override
    public String toString() {
        return "Fork(" + statement.toString() + ")";
    }
}
