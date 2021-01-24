package Statement;
import ADT.*;
import Exception.*;
import Expression.IExpression;
import IType.IType;
import ProgramState.ProgramState;
import IType.StringType;
import Value.*;
import java.io.BufferedReader;
import java.io.IOException;

public class CloseReadFileStatement implements IStatement{
    IExpression expression;

    public CloseReadFileStatement(IExpression exp){
        this.expression = exp;
    }

    @Override
    public String toString(){
        return "closeRFile(" + expression.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException, Exception {
        IValue value = expression.evaluate(state.get_symTable(), state.get_heap());
        if(value.get_type().equals(new StringType())){
            StringValue new_value = (StringValue)value;
            IDictionary<StringValue, BufferedReader> file_table = state.get_fileTable();
            if(file_table.is_defined(new_value)) {
                BufferedReader reader = state.get_fileTable().lookup(new_value);
                try {
                    reader.close();
                    file_table.remove(new_value);
                }catch(IOException exc){
                    throw new MyException(exc.getMessage());
                }
            }
            else throw new UndefinedVariableException("File does not exist.");
        }
        else throw new MyException("File name is not a string");
        return null;
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeTable) throws MyException {
        if (!expression.typeCheck(typeTable).equals(new StringType()))
            throw new MyException("ERROR: CloseReadFile requires a string expression");
        return typeTable;
    }
}
