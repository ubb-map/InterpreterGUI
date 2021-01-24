package Statement;
import ADT.IDictionary;
import Exception.MyException;
import ProgramState.ProgramState;
import IType.IType;
import Value.IValue;

public class VariableDeclarationStatement implements IStatement{
    String name;
    IType type;

    public VariableDeclarationStatement(String name, IType type){
        this.name = name;
        this.type = type;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        IDictionary<String, IValue> symTable = state.get_symTable();

        if (!symTable.is_defined(name)) {
            symTable.update(name, this.type.DefaultValue());
        } else throw new MyException("the used variable" + name + " was declared before!");

        return null;
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeTable) throws MyException {
        typeTable.update(name, type);
        return typeTable;
    }

    @Override
    public String toString() {
        return this.type.toString() + " " + this.name;
    }
}
