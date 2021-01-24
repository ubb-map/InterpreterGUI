package Statement;
import Exception.*;
import ADT.IDictionary;
import Expression.IExpression;
import ProgramState.ProgramState;
import IType.*;
import Value.*;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFileStatement implements IStatement{
    IExpression expression;
    String variable_name;

    public ReadFileStatement(IExpression expr, String var_name){
        this.expression = expr;
        this.variable_name = var_name;
    }

    @Override
    public String toString(){
        return "ReadFile(" + expression.toString() + "," + variable_name + ")";
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException, Exception {
        IDictionary<String, IValue> dict = state.get_symTable();

        if(dict.is_defined(variable_name)){
            if(dict.lookup(variable_name).get_type().equals(new IntType())){
                IValue value = expression.evaluate(dict, state.get_heap());
                if(value.get_type().equals(new StringType())){
                    StringValue name = (StringValue)value;
                    IDictionary<StringValue, BufferedReader> file_table = state.get_fileTable();
                    if(file_table.is_defined(name)){
                        BufferedReader reader = file_table.lookup(name);
                        try{
                            String line = reader.readLine();
                            if(line != null){
                                IntValue new_value = new IntValue(Integer.parseInt(line));
                                dict.update(variable_name, new_value);
                            }
                        }
                        catch(IOException exc){
                            throw new MyException(exc.getMessage());
                        }
                    }
                    else throw new MyException("The file name is undefined");
                }
            }
            else throw new MyException("Invalid type");
        }
        else throw new UndefinedVariableException("Undefined variable");
        return null;
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeTable) throws MyException {
        if (!expression.typeCheck(typeTable).equals(new StringType()))
            throw new MyException("ERROR: ReadFile requires a string as expression parameter");
        if (!typeTable.lookup(variable_name).equals(new IntType()))
            throw new MyException("ERROR: ReadFile requires an int as variable parameter");
        return typeTable;
    }
}
