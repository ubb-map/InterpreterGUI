package Expression;
import ADT.IHeap;
import ADT.MyDictionary;
import Exception.MyException;
import ADT.IDictionary;
import IType.IType;
import Value.IValue;
import com.sun.jdi.Value;

public class ValueExpression implements IExpression{
    IValue expr;

    public ValueExpression(IValue expression){
        this.expr = expression;
    }

    @Override
    public String toString(){
        return expr.toString();
    }

    @Override
    public IValue evaluate(IDictionary<String, IValue> tbl, IHeap<IValue> heap) throws MyException {
        return expr;
    }

    @Override
    public IType typeCheck(IDictionary<String, IType> typeTable) throws MyException {
        return expr.get_type();
    }
}
