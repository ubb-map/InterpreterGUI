package IType;

import Value.IValue;
import Value.StringValue;

public class StringType implements IType{

    public boolean equals(Object another){
        if(another instanceof  StringType)
            return true;
        return false;
    }

    @Override
    public String toString(){
        return "String";
    }

    public IValue DefaultValue(){
        return new StringValue("");
    }
}
