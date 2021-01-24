package Statement;
import ADT.IDictionary;
import ADT.IStack;
import Exception.*;
import Expression.*;
import IType.*;
import Value.*;
import ProgramState.ProgramState;

public class ForStatement implements IStatement{
    String variable;
    IExpression exp1, exp2, exp3;
    IStatement statement;

    public ForStatement(String variable, IExpression ex1, IExpression ex2, IExpression ex3, IStatement statement) throws Exception, MyException {
        this.variable = variable;
        this.exp1 = ex1;
        this.exp2 = ex2;
        this.exp3 = ex3;
        this.statement = statement;

    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception, MyException {
        if(!((exp1.evaluate(state.get_symTable(), state.get_heap())) instanceof IntValue))
            throw new Exception("Error");

        if(!((exp1.evaluate(state.get_symTable(), state.get_heap())) instanceof IntValue))
            throw new Exception("Error");

        if(!((exp3.evaluate(state.get_symTable(), state.get_heap())) instanceof IntValue))
            throw new Exception("Error");

        IStack<IStatement> stack = state.get_exeStack();
        IStatement newStatement = new CompoundStatement(new AssignmentStatement("v", exp1),
                new WhileStatement(new RelationalExpression(new VariableExpression("v"), exp2, 1), new CompoundStatement(this.statement,
                        new AssignmentStatement("v", exp3))));
        stack.push(newStatement);
        return null;
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeTable) throws MyException {
        return typeTable;
    }

    @Override
    public String toString(){
        return "for(" + variable + "= " + exp1.toString() + "; " + variable + "= " + exp2.toString() + "; " + variable + "= " + exp3.toString() + ") " +
                this.statement.toString();
    }
}
