package Statement;

import ADT.IDictionary;
import ADT.IHeap;
import Expression.IExpression;
import IType.IType;
import ProgramState.ProgramState;
import IType.ReferenceType;
import Value.IValue;
import Exception.*;
import Value.ReferenceValue;

public class WriteHeapStatement implements IStatement{
    String variable_name;
    IExpression expression;

    public WriteHeapStatement(String variable, IExpression expression){
        variable_name = variable;
        this.expression = expression;
    }

    @Override
    public String toString(){
        return "writeHeap( " + variable_name + ", " + expression + " )";
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        IDictionary<String, IValue> symbolTable = state.get_symTable();
        IHeap heap = state.get_heap();

        if(!symbolTable.is_defined(variable_name))
            throw new MyException(variable_name + "is not defined");

        IValue variable = symbolTable.lookup(variable_name);
        if(!(variable.get_type() instanceof ReferenceType))
            throw new MyException(variable_name + "is not of type Reference");

        int address = ((ReferenceValue) variable).get_address();

        if(!heap.is_defined(address))
            throw new MyException(variable_name + "is not defined in the heap");

        IValue value = expression.evaluate(symbolTable, heap);

        if(!value.get_type().equals(((ReferenceValue)variable).get_location_type()))
            throw new MyException(value + "is of " + ((ReferenceValue)variable).get_location_type() + " type" );

        heap.update(address, value);

        return null;
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeTable) throws MyException {
        if (typeTable.lookup(variable_name).equals(new ReferenceType(expression.typeCheck(typeTable))))
            return typeTable;
        throw new MyException("WriteHeap: right hand side and left hand side have different types");
    }
}
