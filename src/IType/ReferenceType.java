package IType;
import Value.*;

public class ReferenceType implements IType{
    IType inner;

    public ReferenceType(IType inner) {
        this.inner=inner;
    }

    public IType getInner() {
        return inner;
    }

    public boolean equals(Object another){
        if (another instanceof  ReferenceType)
            return inner.equals((((ReferenceType) another).getInner()));
        else
            return false;
    }

    @Override
    public String toString() {
        return "Ref(" +inner.toString()+")";
    }

    @Override
    public IValue DefaultValue() {
        return new ReferenceValue(0, inner);
    }
}
