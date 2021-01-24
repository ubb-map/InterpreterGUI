package Expression;
import ADT.IHeap;
import ADT.MyDictionary;
import Exception.MyException;
import ADT.IDictionary;
import IType.BoolType;
import IType.IType;
import Value.IValue;
import Value.BoolValue;

public class LogicExpression implements IExpression{
    IExpression first_exp, second_exp;
    int operand; // 1 = &&, 2 = ||, 3 = !

    public LogicExpression(IExpression e1, IExpression e2, int operand){
        this.first_exp = e1;
        this.second_exp = e2;
        this.operand = operand;
    }

    public LogicExpression(IExpression e1, int operand){
        this.first_exp = e1;
        IValue v = new BoolValue(false);
        this.second_exp = new ValueExpression(v);
        this.operand = operand;
    }

    @Override
    public IValue evaluate(IDictionary<String, IValue> tbl, IHeap<IValue> heap) throws Exception {
        IValue v1, v2;
        v1 = first_exp.evaluate(tbl, heap);
        if (v1.get_type().equals(new BoolType())) {
            v2 = second_exp.evaluate(tbl, heap);
            if (v2.get_type().equals(new BoolType())) {
                BoolValue i1 = (BoolValue)v1;
                BoolValue i2 = (BoolValue)v2;
                boolean n1, n2;
                n1 = i1.getVal();
                n2 = i2.getVal();
                if (operand == 1)  return new BoolValue(n1 && n2);
                if (operand == 2)  return new BoolValue(n1 || n2);
                if (operand == 3)  return new BoolValue(!n1);

            }else
                throw new MyException("second operand is not a boolean");
        }else
            throw new MyException("first operand is not a boolean");
        return null;
    }

    @Override
    public IType typeCheck(IDictionary<String, IType> typeTable) throws MyException {
        IType type1 = this.first_exp.typeCheck(typeTable), type2 = this.second_exp.typeCheck(typeTable);
        if(!type1.equals(new BoolType()))
            throw new MyException("First expression is not a boolean.");
        if(!type2.equals(new BoolType()))
            throw new MyException("Second expression is not a boolean.");
        return new BoolType();
    }
}
