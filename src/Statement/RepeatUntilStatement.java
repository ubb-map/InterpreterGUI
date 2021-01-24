package Statement;
import ADT.IDictionary;
import ADT.IStack;
import ADT.MyDictionary;
import Exception.*;
import Expression.IExpression;
import Expression.RelationalExpression;
import Expression.ValueExpression;
import IType.IType;
import ProgramState.ProgramState;
import Value.IntValue;

public class RepeatUntilStatement implements IStatement {
    IExpression exp2;
    IStatement stm1;

    public RepeatUntilStatement(IStatement stm1, IExpression exp2){
        this.stm1 = stm1;
        this.exp2 = exp2;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception, MyException {
        IStack<IStatement> stack = state.get_exeStack();
        IStatement newStatement = new CompoundStatement(stm1, new WhileStatement(new RelationalExpression(exp2, new ValueExpression(new IntValue(0)), 4), stm1));
        stack.push(newStatement);
        return null;
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeTable) throws MyException {
        return null;
    }
}
