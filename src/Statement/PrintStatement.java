package Statement;
import ADT.IDictionary;
import ADT.IList;
import ADT.MyDictionary;
import IType.IType;
import ProgramState.ProgramState;
import Expression.*;
import Exception.MyException;

public class PrintStatement implements IStatement{
    IExpression expression;

    public PrintStatement(IExpression e1){
        expression = e1;
    }

    @Override
    public String toString(){
        return "print(" + expression.toString() + ")" ;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {

        try {
            IList out = state.get_out();
            out.add(expression.evaluate(state.get_symTable(), state.get_heap()).toString());
        }catch (MyException exception){
            throw new MyException(exception.getMessage());
        }

        return null;
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeTable) throws MyException {
        expression.typeCheck(typeTable);
        return typeTable;
    }
}
