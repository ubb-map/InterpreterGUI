package Statement;
import ADT.IDictionary;
import ADT.IHeap;
import ADT.IStack;
import ProgramState.ProgramState;
import IType.IType;
import Expression.*;
import Value.*;
import Exception.*;

public class AssignmentStatement implements IStatement{
    String variable;
    IExpression expression;

    @Override
    public String toString(){
        return variable + "=" + expression.toString();
    }

    public AssignmentStatement(String variable, IExpression e){
        this.variable = variable;
        this.expression = e;
    }

    /*@Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeTable) throws MyException {
        if (!typeTable.lookup(variable).equals(expression.typeCheck(typeTable)))
            throw new MyException("Assignment: right hand side and left hand side have different types");
        return typeTable;
        return null;
    }*/

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        IStack<IStatement> stk = state.get_exeStack();
        IHeap<IValue> heap = state.get_heap();
        IDictionary<String, IValue> symTable = state.get_symTable();
        if(symTable.is_defined(variable)) {
            IValue value = expression.evaluate(symTable, heap);
            IType variable_type = symTable.lookup(variable).get_type();
            if (value.get_type().equals(variable_type)) {
                symTable.update(variable, value);
            }
            else throw new MyException("declared type of variable" + variable + " and type of  the assigned expression do not match");
        }
        else throw new UndefinedVariableException("the used variable" + variable + " was not declared before");
        return null;
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeTable) throws MyException {
        if (!typeTable.lookup(variable).equals(expression.typeCheck(typeTable)))
            throw new MyException("Assignment: right hand side and left hand side have different types");
        return typeTable;
    }
}
