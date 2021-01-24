package Expression;
import ADT.IHeap;
import ADT.MyDictionary;
import Exception.*;
import ADT.IDictionary;
import IType.IType;
import Value.IValue;

public class VariableExpression implements IExpression{
    String variable;

    public VariableExpression(String v){
        variable = v;
    }

    @Override
    public String toString(){
        return variable;
    }

    @Override
    public IValue evaluate(IDictionary<String, IValue> table, IHeap<IValue> heap) throws Exception {
        if(!table.is_defined(variable))
            throw new UndefinedVariableException("Variable " + variable + " is not defined!");
        return table.lookup(variable);
    }

    @Override
    public IType typeCheck(IDictionary<String, IType> typeTable) throws MyException {
        return typeTable.lookup(variable);
    }
}
