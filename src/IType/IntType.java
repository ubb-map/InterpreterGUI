package IType;
import Value.*;

public class IntType implements IType {

    public boolean equals(Object another){
        if(another instanceof  IntType)
            return true;
        return false;
    }

    @Override
    public String toString() { return "int";}

    @Override
    public IValue DefaultValue() {
        return new IntValue(0);
    }
}
