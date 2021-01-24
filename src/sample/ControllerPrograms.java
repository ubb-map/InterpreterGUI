package sample;

import ADT.*;
import IType.*;
import Controller.Controller;
import Expression.*;
import IType.IType;
import IType.ReferenceType;
import ProgramState.ProgramState;
import Repository.IRepository;
import Statement.*;
import Value.*;
import com.sun.jdi.Value;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;
import Exception.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ControllerPrograms {

    @FXML
    private ListView<IStatement> listView;

    @FXML
    private Button switchToMainWindow;

    @FXML
    public void initialize() throws Exception {
        listView.setItems(getCommands());
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listView.getSelectionModel().selectIndices(0);
    }

    @FXML
    public void handleButtonSwitchToMainWindow(javafx.event.ActionEvent event) throws IOException, MyException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "MainWindow.fxml"
                )
        );
        Stage stage;
        Parent root;
        stage=(Stage) switchToMainWindow.getScene().getWindow();
        root = loader.load();

        ControllerMainWindow controller = loader.getController();
        controller.initData(listView.getSelectionModel().getSelectedItem());


        Scene scene = new Scene(root, 900, 900);
        stage.setScene(scene);
        stage.show();

    }

    private ObservableList<IStatement> getCommands() throws Exception {
        IStatement ex1 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v"))));

        IStatement ex2 = new CompoundStatement(new VariableDeclarationStatement("a",new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("b",new IntType()),
                        new CompoundStatement(new AssignmentStatement("a",
                                new ArithmeticExpression(new ValueExpression(new IntValue(2)),
                                        new ArithmeticExpression(new ValueExpression(new IntValue(3)),
                                                new ValueExpression(new IntValue(5)),3),1)),
                                new CompoundStatement(new AssignmentStatement("b",
                                        new ArithmeticExpression(new VariableExpression("a"),
                                                new ValueExpression(new IntValue(1)),1)),
                                        new PrintStatement(new VariableExpression("b"))))));

        IStatement ex3 = new CompoundStatement(new VariableDeclarationStatement("a",new BoolType()),
                new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                        new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new BoolValue(false))),
                                new CompoundStatement(new IfStatement(new VariableExpression("a"),
                                        new AssignmentStatement("v",new ValueExpression(new IntValue(2))),
                                        new AssignmentStatement("v", new ValueExpression(new IntValue(3)))),
                                        new PrintStatement(new VariableExpression("v"))))));


        IStatement ex4 = new CompoundStatement(new VariableDeclarationStatement("v",new IntType()),
                new CompoundStatement(new AssignmentStatement("v",new ValueExpression(new IntValue(2))), new PrintStatement(new VariableExpression("e"))));


        IStatement ex5 = new CompoundStatement(new VariableDeclarationStatement("a",new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("b",new IntType()),
                        new CompoundStatement(new AssignmentStatement("a",
                                new ArithmeticExpression(new ValueExpression(new IntValue(2)),
                                        new ArithmeticExpression(new ValueExpression(new IntValue(3)),
                                                new ValueExpression(new IntValue(0)),4),1)),
                                new CompoundStatement(new AssignmentStatement("b",
                                        new ArithmeticExpression(new VariableExpression("a"),
                                                new ValueExpression(new IntValue(1)),1)),
                                        new PrintStatement(new VariableExpression("b"))))));


        IStatement ex6 = new CompoundStatement(new VariableDeclarationStatement("varf", new StringType()),
                new CompoundStatement(new AssignmentStatement("varf", new ValueExpression(new StringValue("test.in"))),
                        new CompoundStatement(new OpenReadFileStatement(new VariableExpression("varf")),
                                new CompoundStatement(new VariableDeclarationStatement("varc", new IntType()),
                                        new CompoundStatement(new ReadFileStatement(new VariableExpression("varf"), "varc"),
                                                new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                        new CompoundStatement(new ReadFileStatement(new VariableExpression("varf"), "varc"),
                                                                new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                                        new CloseReadFileStatement(new VariableExpression("varf"))))))))));


        // int v; v=4; (while (v>0) print(v);v=v-1);print(v)
        IStatement ex7 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(4))),
                        new CompoundStatement(new WhileStatement(new RelationalExpression(new VariableExpression("v"), new ValueExpression(new IntValue(0)), 5),
                                new CompoundStatement(new PrintStatement(new VariableExpression("v")), new AssignmentStatement("v",
                                        new ArithmeticExpression(new VariableExpression("v"), new ValueExpression(new IntValue(1)), 2)))),
                                new PrintStatement(new VariableExpression("v")))));

        IStatement ex8 = new CompoundStatement(
                new VariableDeclarationStatement("x", new IntType()),
                new CompoundStatement(
                        new VariableDeclarationStatement("y", new IntType()),
                        new CompoundStatement(
                                new AssignmentStatement("x", new ValueExpression(new IntValue(4))),
                                new CompoundStatement(
                                        new AssignmentStatement("y", new ValueExpression(new IntValue(6))),
                                        new PrintStatement(new RelationalExpression(new VariableExpression("x"), new VariableExpression("y"), 1))))));



        IStatement ex9 = new CompoundStatement(
                new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
                new CompoundStatement(
                        new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(
                                new VariableDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                                new CompoundStatement(
                                        new HeapAllocationStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(
                                                new PrintStatement(new VariableExpression("v")),
                                                new PrintStatement(new VariableExpression("a")))))));


        IStatement ex10 = new CompoundStatement(
                new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
                new CompoundStatement(
                        new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(
                                new PrintStatement(new ReadHeapExpression(new VariableExpression("v"))),
                                new CompoundStatement(
                                        new WriteHeapStatement("v", new ValueExpression(new IntValue(30))),
                                        new PrintStatement(new ArithmeticExpression(new ReadHeapExpression(new VariableExpression("v")), new ValueExpression(new IntValue(5)),1))

                                )
                        )
                )
        );


        IStatement ex11 = new CompoundStatement(new VariableDeclarationStatement("v",new ReferenceType(new IntType())),
                new CompoundStatement(
                        new HeapAllocationStatement("v",new ValueExpression(new IntValue(20))),
                        new CompoundStatement(
                                new VariableDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                                new CompoundStatement(
                                        new HeapAllocationStatement("a",new VariableExpression("v")),
                                        new CompoundStatement(
                                                new HeapAllocationStatement("v",new ValueExpression(new IntValue(30))),
                                                new PrintStatement(new ReadHeapExpression(new ReadHeapExpression(new VariableExpression("a"))))
                                        )
                                )
                        )
                ));

        IStatement ex12 = new CompoundStatement(
                new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
                new CompoundStatement(
                        new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(
                                new PrintStatement(new ReadHeapExpression(new VariableExpression("v"))),
                                new CompoundStatement(
                                        new WriteHeapStatement("v", new ValueExpression(new IntValue(30))),
                                        new PrintStatement(new ArithmeticExpression(new ReadHeapExpression(new VariableExpression("v")), new ValueExpression(new IntValue(5)),1))

                                )
                        )
                )
        );



        IStatement ex13 = new CompoundStatement(new VariableDeclarationStatement("v",new ReferenceType(new IntType())),
                new CompoundStatement(
                        new HeapAllocationStatement("v",new ValueExpression(new IntValue(20))),
                        new CompoundStatement(
                                new VariableDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                                new CompoundStatement(
                                        new HeapAllocationStatement("a",new VariableExpression("v")),
                                        new CompoundStatement(
                                                new HeapAllocationStatement("v",new ValueExpression(new IntValue(30))),
                                                new PrintStatement(new ReadHeapExpression(new ReadHeapExpression(new VariableExpression("a"))))
                                        )
                                )
                        )
                ));

        IStatement ex14 = new CompoundStatement(
                new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(
                        new VariableDeclarationStatement("a", new ReferenceType(new IntType())),
                        new CompoundStatement(
                                new AssignmentStatement("v", new ValueExpression(new IntValue(10))),
                                new CompoundStatement(
                                        new HeapAllocationStatement("a", new ValueExpression(new IntValue(22))),
                                        new CompoundStatement(
                                                new ForkStatement(
                                                        new CompoundStatement(
                                                                new WriteHeapStatement("a", new ValueExpression(new IntValue(30))),
                                                                new CompoundStatement(
                                                                        new AssignmentStatement("v", new ValueExpression(new IntValue(32))),
                                                                        new CompoundStatement(
                                                                                new PrintStatement(new VariableExpression("v")),
                                                                                new PrintStatement(new ReadHeapExpression(new VariableExpression("a")))
                                                                        )
                                                                )

                                                        )
                                                ),
                                                new CompoundStatement(
                                                        new PrintStatement(new VariableExpression("v")),
                                                        new PrintStatement(new ReadHeapExpression(new VariableExpression("a")))))))));

        IStatement ex15 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new ForStatement("v", new ValueExpression(new IntValue(0)), new ValueExpression(new IntValue(3)), new ArithmeticExpression(new VariableExpression("v"), new ValueExpression(new IntValue(1)), 1),
                                new ForkStatement(new CompoundStatement(new PrintStatement(new VariableExpression("v")), new AssignmentStatement("v", new ArithmeticExpression(new VariableExpression("v"), new ValueExpression(new IntValue(1)), 1))))),
                                    new PrintStatement(new ArithmeticExpression(new VariableExpression("v"), new ValueExpression(new IntValue(10)), 3)))));

        IStatement ex16 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new ForStatement("v", new ValueExpression(new IntValue(17)), new ValueExpression(new IntValue(31)),
                        new ArithmeticExpression(new VariableExpression("v"), new ValueExpression(new IntValue(1)), 1), new PrintStatement(new VariableExpression("v"))));

        IStatement ex17 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new WaitStatement(new ValueExpression(new IntValue(10))),
                                new PrintStatement(new ArithmeticExpression(new VariableExpression("v"),
                                        new ValueExpression(new IntValue(10)), 3)))));

        IStatement ex18 = new CompoundStatement(new VariableDeclarationStatement("a", new IntType()),
                new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new IntValue(1))),
                        new CompoundStatement(new VariableDeclarationStatement("b", new IntType()),
                                new CompoundStatement(new AssignmentStatement("b", new ValueExpression(new IntValue(2))),
                                new CompoundStatement(new VariableDeclarationStatement("c", new IntType()),
                                        new CompoundStatement(new AssignmentStatement("c", new ValueExpression(new IntValue(5))),
                                                new CompoundStatement(new SwitchStatement(new ArithmeticExpression(new VariableExpression("a"),
                                                        new ValueExpression(new IntValue(10)), 3), new ArithmeticExpression(new VariableExpression("a"), new VariableExpression("b"), 3), new ValueExpression(new IntValue(10)),
                                                        new CompoundStatement(new PrintStatement(new VariableExpression("a")), new PrintStatement(new VariableExpression("b"))),
                                                        new CompoundStatement(new PrintStatement(new ValueExpression(new IntValue(100))), new PrintStatement(new ValueExpression(new IntValue(200)))),
                                                        new PrintStatement(new ValueExpression(new IntValue(200)))),
                                                        new PrintStatement(new ValueExpression(new IntValue(300))))))))));

        //v = 10 repeat v = v+1 until v = 20
       /* IStatement ex19 = new CompoundStatement(new VariableDeclarationStatement("a", new IntType()),
                new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new IntValue(10))),
                        new CompoundStatement(
                        new RepeatUntilStatement(new CompoundStatement(new AssignmentStatement("v",
                                new ArithmeticExpression(new VariableExpression("v"), new ValueExpression(new IntValue(1)), 1)),
                                new PrintStatement(new VariableExpression("v"))), new ValueExpression(new IntValue(10)), 3))));
*/

        ObservableList<IStatement> commands = FXCollections.observableArrayList(ex1, ex3, ex4, ex5, ex6, ex7, ex8, ex9, ex10, ex11, ex12, ex13, ex14, ex15, ex17, ex18);
        return commands;
    }
}
