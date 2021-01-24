package Statement;
import ADT.IDictionary;
import Exception.*;
import Expression.IExpression;
import ProgramState.ProgramState;
import IType.*;
import Value.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenReadFileStatement implements IStatement{
    IExpression expression;

    public OpenReadFileStatement(IExpression expr){
        this.expression = expr;
    }

    @Override
    public String toString(){
        return "openRFile(" + expression.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException, Exception {
        IValue value = this.expression.evaluate(state.get_symTable(), state.get_heap());

        if(value.get_type().equals(new StringType())){
            StringValue file = (StringValue)value;
            IDictionary<StringValue, BufferedReader> file_table = state.get_fileTable();
            if(!file_table.is_defined(file)){
                try{
                    BufferedReader reader = new BufferedReader(new FileReader(file.get_value()));
                    file_table.update(file, reader);
                }catch(FileNotFoundException exc){
                    throw new MyException(exc.getMessage());
                }
            }
            else throw new MyException("Already existent file.");
        }
        else throw new MyException("The expression is not a string type.");
        return null;
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeTable) throws MyException {
        IType type1 = expression.typeCheck(typeTable);
        if(!type1.equals(new StringType()))
            throw new MyException("Expression is not a string.");
        return typeTable;
    }
}
