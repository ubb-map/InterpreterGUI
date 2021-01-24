package Expression;

import ADT.*;
import ADT.MyDictionary;
import IType.*;
import Value.*;
import Exception.*;
import Value.IntValue;

public class RelationalExpression implements IExpression{
    IExpression first, second;
    int operand;

    public RelationalExpression(IExpression e1, IExpression e2, int operand){
        this.first = e1;
        this.second = e2;
        this.operand = operand;
    }

    @Override
    public IValue evaluate(IDictionary<String, IValue> table, IHeap<IValue> heap) throws MyException, Exception {
        IValue v1, v2;
        v1 = first.evaluate(table, heap);
        v2 = second.evaluate(table, heap);

        if(v1.get_type().equals(new IntType()) && v1.get_type().equals(v2.get_type())){
            IntValue i1 = (IntValue)v1;
            IntValue i2 = (IntValue)v2;

            int n1, n2;
            n1= i1.getVal();
            n2 = i2.getVal();

            switch(operand){
                case 1:
                    return new BoolValue(n1 < n2);
                case 2:
                    return new BoolValue(n1<=n2);
                case 3:
                    return new BoolValue(n1 == n2);
                case 4:
                    return new BoolValue(n1 != n2);
                case 5:
                    return new BoolValue(n1>n2);
                case 6:
                    return new BoolValue(n1>=n2);
            }
        }
        else throw new MyException("Operands have incorrect values.");
        return null;
    }

    @Override
    public IType typeCheck(IDictionary<String, IType> typeTable) throws MyException {
        IType type1, type2;
        type1 = first.typeCheck(typeTable);
        type2 = second.typeCheck(typeTable);
        if (type1.equals(new IntType())) {
            if (type2.equals(new IntType()))
                return new BoolType();
            else
                throw new MyException("Second operand is not an integer");
        }else
            throw new MyException("First operand is not an integer");
    }

    @Override
    public String toString(){
        String symbol = "";
        switch(operand){
            case 1:
                symbol = "<";
                break;
            case 2:
                symbol = "<=";
                break;
            case 3:
                symbol = "==";
                break;
            case 4:
                symbol = "!=";
                break;
            case 5:
                symbol = ">";
                break;
            case 6:
                symbol = ">=";
                break;
        }
        return this.first.toString() + symbol + this.second.toString();
    }
}
