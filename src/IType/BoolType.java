package IType;

import Value.BoolValue;
import Value.IValue;

public class BoolType implements IType {
    public boolean equals(Object another){
        if(another instanceof  BoolType)
            return true;
        return false;
    }

    @Override
    public String toString() { return "boolean";}

    @Override
    public IValue DefaultValue() {
        return new BoolValue(false);
    }
}
