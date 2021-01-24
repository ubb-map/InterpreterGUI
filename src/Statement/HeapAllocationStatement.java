package Statement;
import ADT.IHeap;
import Exception.*;
import ADT.IDictionary;
import Expression.IExpression;
import ProgramState.ProgramState;
import Value.*;
import IType.*;

public class HeapAllocationStatement implements IStatement{
    String variable_name;
    IExpression expression;

    public HeapAllocationStatement(String variable_name, IExpression expression){
        this.variable_name = variable_name;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        IDictionary<String, IValue> symTable = state.get_symTable();
        IHeap<IValue> heap = state.get_heap();
        if(symTable.is_defined(variable_name)){
            if(symTable.lookup(variable_name).get_type() instanceof ReferenceType) {

                IValue value = expression.evaluate(symTable, state.get_heap());
                IValue locationType=symTable.lookup(variable_name);

                if(value.get_type().equals(((ReferenceType)(locationType.get_type())).getInner())) {
                    int address= heap.add(value);
                    symTable.update(variable_name, new ReferenceValue(address, value.get_type()));
                }
                else throw new MyException("The variable " + variable_name + " and the assigned value do not match!");
            }
            else throw new MyException("the variable " + variable_name + " is not of type Reference");

        }
        else throw new UndefinedVariableException("Undefined variable");
        return null;
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeTable) throws MyException {
        IType typeVariable = typeTable.lookup(variable_name);
        IType typeExpression = expression.typeCheck(typeTable);
        if (!typeVariable.equals(new ReferenceType(typeExpression)))
            throw new MyException("New: right hand side and left hand side have different types ");
        return typeTable;
    }

    @Override
    public String toString(){
        return "new(" + variable_name + ", " + expression.toString() + ")";
    }
}
