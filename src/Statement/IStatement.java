package Statement;
import ADT.IDictionary;
import ADT.MyDictionary;
import Exception.MyException;
import ProgramState.ProgramState;
import IType.IType;

public interface IStatement {
    public ProgramState execute(ProgramState state) throws Exception, MyException;
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeTable) throws MyException;
    public String toString();
}
