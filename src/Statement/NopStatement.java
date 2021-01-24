package Statement;
import ADT.IDictionary;
import Exception.MyException;
import IType.IType;
import ProgramState.ProgramState;

public class NopStatement implements IStatement{

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        return null;
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeTable) throws MyException {
        return typeTable;
    }
}
