package Expression;
import ADT.IHeap;
import ADT.MyDictionary;
import Exception.MyException;
import ADT.IDictionary;
import IType.IType;
import Value.IValue;

import java.util.Dictionary;

public interface IExpression {
    public String toString();
    public IValue evaluate(IDictionary<String, IValue> tbl, IHeap<IValue> heap) throws MyException, Exception;
    public IType typeCheck(IDictionary<String, IType> typeTable) throws MyException;
}
