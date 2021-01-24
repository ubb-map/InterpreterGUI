package Statement;
import ADT.IDictionary;
import IType.*;
import ProgramState.ProgramState;
import Expression.*;
import Exception.MyException;
import Value.BoolValue;
import Value.IValue;

public class IfStatement implements IStatement{
    IExpression expression;
    IStatement then_st, else_st;


    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        IValue a = new BoolValue(false);
        IValue ifStatement = expression.evaluate(state.get_symTable(), state.get_heap());
        if (ifStatement.get_type().equals(a.get_type())){
            if(ifStatement.equals(true)){
                then_st.execute(state);
            }
            else{
                else_st.execute(state);
            }
        }
        else{
            throw new MyException("If statement is not a boolean value!");
        }
        return null;
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeTable) throws MyException {
        IType type1 = expression.typeCheck(typeTable);
        if(!type1.equals(new BoolType()))
            throw new MyException("Expression is not a boolean.");
        then_st.typeCheck(typeTable);
        else_st.typeCheck(typeTable);
        return typeTable;

    }

    public IfStatement(IExpression e, IStatement then_st, IStatement else_st) {
        expression = e;
        this.then_st = then_st;
        this.else_st = else_st;
    }

    @Override
    public String toString() {
        return "(IF(" + expression.toString() + ") THEN(" + then_st.toString() + ")ELSE(" + else_st.toString()+"))";
    }

}
