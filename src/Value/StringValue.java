package Value;
import IType.IType;
import IType.StringType;
import com.sun.jdi.BooleanValue;

public class StringValue implements IValue{
    String val;

    public StringValue(String a){
        this.val = a;
    }

    @Override
    public IType get_type() {
        return new StringType();
    }

    @Override
    public boolean equals(Object v) {
        return equals(val);
    }

    public String toString(){
        return this.val;
    }

    public String get_value(){
        return this.val;
    }

    @Override
    public IValue copy() {
        return new StringValue(val);
    }
}
