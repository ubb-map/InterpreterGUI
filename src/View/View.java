package View;
import ProgramState.ProgramState;
import Controller.*;

import java.util.ArrayList;
import java.util.Scanner;

public class View {
    Controller controller;
    public View(Controller controller){
        this.controller = controller;
    }

    public void run(){
        while(true){

            System.out.println("Type 1 to see the list of programs to execute!");
            System.out.println("Type 0 to exit!");

            Scanner choice = new Scanner(System.in);

            if(choice.nextInt() == 1){
                int index = 1;
                for(ProgramState i : controller.get_all()) {
                    System.out.println("Input " + index + ":\n " + i.display());
                    index +=1;
                }

                System.out.println("Choose a program: ");
                choice = new Scanner(System.in);
                int program = choice.nextInt();

                try {
                    ;
                    //controller.allStep();
                }
                catch(Exception exception)
                {
                    System.out.println(exception.getMessage());
                    System.out.println("COMPILATION FAILED\n");
                }

            }
            else
                return;
        }
    }
}
