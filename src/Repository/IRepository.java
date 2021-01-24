package Repository;
import ProgramState.ProgramState;
import Exception.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface IRepository {
    public void add(ProgramState program);
    public void logProgramStatementExecution(ProgramState state) throws MyException;
    public void clear_file() throws FileNotFoundException;
    public List<ProgramState> get_all();
    public void setProgramStates(List<ProgramState> list);
    public String get_log_file_path();
    public ProgramState getByID(int id);
}
