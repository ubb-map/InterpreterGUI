package Statement;
import ADT.IDictionary;
import ADT.IStack;
import IType.IType;
import ProgramState.ProgramState;
import Exception.MyException;

public class CompoundStatement implements IStatement{
    IStatement first, second;

    public CompoundStatement(IStatement e1, IStatement e2){
        first = e1;
        second = e2;
    }

    @Override
    public String toString() {
        return "(" + first.toString() + ";" + second.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        IStack<IStatement> stack = state.get_exeStack();
        stack.push(second);
        stack.push(first);
        return null;
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeTable) throws MyException {
        return second.typeCheck(first.typeCheck(typeTable));
    }
}