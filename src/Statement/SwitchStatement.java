package Statement;
import ADT.IDictionary;
import ADT.IStack;
import Exception.*;
import Expression.IExpression;
import Expression.RelationalExpression;
import IType.IType;
import ProgramState.ProgramState;
import Value.IntValue;

public class SwitchStatement implements IStatement{
    IExpression exp, exp1, exp2;
    IStatement stm1, stm2, stm3;

    public SwitchStatement(IExpression exp, IExpression exp1, IExpression exp2, IStatement stm1, IStatement stm2, IStatement stm3){
        this.exp = exp;
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.stm1 = stm1;
        this.stm2 = stm2;
        this.stm3 = stm3;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception, MyException {
        if(!((exp1.evaluate(state.get_symTable(), state.get_heap())) instanceof IntValue))
            throw new Exception("Error");

        if(!((exp2.evaluate(state.get_symTable(), state.get_heap())) instanceof IntValue))
            throw new Exception("Error");

        if(!((exp.evaluate(state.get_symTable(), state.get_heap())) instanceof IntValue))
            throw new Exception("Error");
        IStack<IStatement> stack = state.get_exeStack();

        IStatement newStatement = new IfStatement(new RelationalExpression(exp, exp1, 3),stm1,
                new IfStatement(new RelationalExpression(exp, exp2, 3), stm2, stm3));

        stack.push(newStatement);

        return null;
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeTable) throws MyException {
        return typeTable;
    }

    @Override
    public String toString(){
        return "switch(" + exp.toString() + ")\n" +
                "(case(" + exp1.toString() + ")  " + stm1.toString() + ")\n" +
                "(case(" + exp2.toString() + ")  " + stm2.toString() + ")\n" +
                "(default " + stm3.toString() + ")\n" ;
    }
}
