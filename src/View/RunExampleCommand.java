package View;
import Controller.*;
import Exception.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class RunExampleCommand extends Command{
    private Controller ctr;

    public RunExampleCommand(String key, String desc, Controller ctr){
        super(key, desc);
        this.ctr = ctr;
    }

    @Override
    public void execute() throws MyException, IOException {
        try{
            ctr.allStepsExecution();
        } catch (Exception exc) {
            Path fileName = Path.of(ctr.getFile());
            String allLines = Files.readString(fileName);
            Files.writeString(fileName,  allLines + exc.getMessage());
        }
    } //here you must treat the exceptions that can not be solved in the controller    }
}
