package Expression;
import ADT.MyDictionary;
import Exception.*;
import ADT.IDictionary;
import ADT.IHeap;
import Value.*;
import IType.*;

import java.lang.ref.Reference;

public class ReadHeapExpression implements IExpression{
    private final IExpression expression;

    public ReadHeapExpression(IExpression Expression){
        this.expression = Expression;
    }

    @Override
    public IValue evaluate(IDictionary<String, IValue> symbolTable, IHeap<IValue> heap) throws Exception, MyException {
        IValue value = expression.evaluate(symbolTable, heap);

        if(!(value.get_type() instanceof ReferenceType))
            throw new MyException(value + " is not of Reference type");

        int address = ((ReferenceValue)value).get_address();

        if(!heap.is_defined(address))
            throw new MyException(value + " is not defined in the heap");

        return heap.lookup(address);
    }

    @Override
    public IType typeCheck(IDictionary<String, IType> typeTable) throws MyException {
        IType typ = expression.typeCheck(typeTable);
        if (typ instanceof ReferenceType) {
            ReferenceType reft =(ReferenceType) typ;
            return reft.getInner();
        } else throw new MyException("the rH argument is not a Ref Type");
    }

    @Override
    public String toString(){
        return "readHeap( " + expression + " )";
    }


}
