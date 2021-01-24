package Expression;

import ADT.IDictionary;
import ADT.IHeap;
import ADT.MyDictionary;
import IType.IType;
import IType.IntType;
import Value.IValue;
import Value.IntValue;
import Exception.MyException;

public class ArithmeticExpression implements IExpression {
    IExpression first_exp, second_exp;
    int operand; //1 - addtion, 2 subtraction, 3-multipilication, 4 - division

    public ArithmeticExpression(IExpression e1, IExpression e2, int operand){
        this.first_exp = e1;
        this.second_exp = e2;
        this.operand = operand;
    }

    @Override
    public String toString(){
        String symbol = "";
        if(operand == 1)
            symbol = "+";
        if(operand == 2)
            symbol = "-";
        if(operand == 3)
            symbol = "*";
        if(operand == 4)
            symbol = "/";

        return this.first_exp.toString() + symbol + this.second_exp.toString();

    }

    public IValue evaluate(IDictionary<String, IValue> tbl, IHeap<IValue>heap)  throws Exception {
        IValue v1, v2;
        v1 = first_exp.evaluate(tbl, heap);
        if (v1.get_type().equals(new IntType())) {
            v2 = second_exp.evaluate(tbl, heap);
            if (v2.get_type().equals(new IntType())) {
                IntValue i1 = (IntValue)v1;
                IntValue i2 = (IntValue)v2;
                int n1, n2;
                n1 = i1.getVal();
                n2 = i2.getVal();
                if (operand == 1)  return new IntValue(n1 + n2);
                if (operand == 2)  return new IntValue(n1 - n2);
                if(operand == 3)  return new IntValue(n1*n2);
                if(operand == 4)
                    if(n2 == 0) throw new MyException("division by zero");
                    else  return new IntValue(n1/n2);
            }else
                throw new MyException("second operand is not an integer");
        }else
            throw new MyException("first operand is not an integer");
        return null;
    }

    @Override
    public IType typeCheck(IDictionary<String, IType> typeTable) throws MyException {
        IType typ1, typ2;
        typ1 = first_exp.typeCheck(typeTable);
        typ2 = second_exp.typeCheck(typeTable);
        if (typ1.equals(new IntType())) {
            if(typ2.equals(new IntType())) {
                return new IntType();
            } else throw new MyException("second operand is not an integer");
        }else throw new MyException("first operand is not an integer");
    }
}
