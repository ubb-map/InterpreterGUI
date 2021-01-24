package Value;
import IType.IType;
import IType.*;
import com.sun.jdi.BooleanValue;

public class ReferenceValue implements IValue{
    int address;
    IType locationType;

    public ReferenceValue(int address, IType type){
        this.address = address;
        this.locationType = type;
    }

    @Override
    public IValue copy() {
        return new ReferenceValue(address, locationType);
    }

    public int get_address() {
        return address;
    }

    public IType get_location_type(){
        return locationType;
    }

    @Override
    public IType get_type() {
        return new ReferenceType(locationType);
    }

    @Override
    public boolean equals(Object v) {
        return false;
    }

    @Override
    public String toString(){
        return  "(" + address + "," + locationType.toString() + ")";
    }
}