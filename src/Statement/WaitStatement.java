package Statement;
import ADT.IDictionary;
import ADT.IStack;
import Exception.*;
import Expression.ArithmeticExpression;
import Expression.IExpression;
import Expression.ValueExpression;
import IType.IType;
import ProgramState.ProgramState;
import Value.IValue;
import Value.IntValue;

public class WaitStatement implements IStatement{
    IExpression number;

    public WaitStatement(IExpression exp){
        this.number = exp;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception, MyException {
        if(!(this.number.evaluate(state.get_symTable(), state.get_heap()) instanceof IntValue))
            throw new MyException("Invalid");
        IValue nr = this.number.evaluate(state.get_symTable(), state.get_heap());
        if(!(nr instanceof IntValue))
            throw new MyException("Invalid");
        int value = ((IntValue) nr).getVal();

        IStack<IStatement> stack = state.get_exeStack();
        if(value != 0){
            stack.push(new CompoundStatement(new PrintStatement(number), new WaitStatement(new ArithmeticExpression(number, new ValueExpression(new IntValue(1)), 2))));
        }
        return null;
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeTable) throws MyException {
        return typeTable;
    }

    @Override
    public String toString(){
        return "wait(" + number.toString() + ")";
    }
}
