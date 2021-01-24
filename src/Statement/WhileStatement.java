package Statement;
import ADT.IDictionary;
import ADT.IStack;
import Expression.IExpression;
import IType.*;
import Exception.*;
import ProgramState.ProgramState;
import Value.*;

public class WhileStatement implements IStatement{
    IExpression condition;
    IStatement statement;

    public WhileStatement(IExpression condition, IStatement statement){
        this.condition = condition;
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        IDictionary<String, IValue> symbols_table = state.get_symTable();
        IStack<IStatement> stack = state.get_exeStack();
        IValue value = condition.evaluate(symbols_table, state.get_heap());
        if(value instanceof BoolValue){
            BoolValue new_value = (BoolValue)value;
            if(new_value.getVal()){
                stack.push(new WhileStatement(condition, statement));
                stack.push(statement);
            }
        }
        return null;
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeTable) throws MyException {
        IType type = condition.typeCheck(typeTable);
        if (type.equals(new BoolType())) {
            statement.typeCheck(typeTable);
            return typeTable;
        }
        throw new MyException("The condition of While has not the type bool");
    }

    @Override
    public String toString(){
        return "while(" + condition.toString() + ") " + statement.toString();
    }
}
