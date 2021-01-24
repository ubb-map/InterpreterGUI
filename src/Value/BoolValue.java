package Value;

import IType.BoolType;
import IType.IType;
import com.sun.jdi.BooleanValue;

public class BoolValue implements IValue{
    boolean val;

    public BoolValue(boolean v){
        val=v;
    }

    public boolean getVal() {
        return val;
    }

    public String toString() {
        return val + "";
    }

    @Override
    public IType get_type() {
        return new BoolType();
    }

    @Override
    public boolean equals(Object v) {
        if(v.equals(val))
            return true;
        return false;
    }

    @Override
    public IValue copy() {
        return new BoolValue(val);
    }
}
