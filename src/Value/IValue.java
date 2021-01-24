package Value;
import IType.IType;

public interface IValue {
    public IType get_type();
    public boolean equals(Object v);
    IValue copy();
}
