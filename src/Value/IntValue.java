package Value;

import IType.IType;
import IType.IntType;
import com.sun.jdi.BooleanValue;

public class IntValue implements IValue{
    int val;

    public IntValue(int v){
        val=v;
    }

    public int getVal() {
        return val;
    }

    public String toString() {
        return val + "";
    }

    @Override
    public IType get_type() {
        return new IntType();
    }

    @Override
    public boolean equals(Object v) {
        if(v.equals(val))
            return true;
        return false;
    }

    @Override
    public IValue copy() {
        return new IntValue(val);
    }
}
