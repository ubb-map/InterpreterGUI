package View;
import ADT.*;
import Controller.*;
import Exception.*;
import Expression.*;
import ProgramState.ProgramState;
import Repository.*;
import Statement.*;
import IType.*;
import Value.*;


public class Interpreter {
    public static void main(String[] args) throws Exception{
        IStatement ex1 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v"))));

        /*ProgramState p1 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), ex1);
        IRepository repo1 = new Repository("program1.txt");
        repo1.add(p1);
        Controller controller1 = new Controller(repo1);


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

        ProgramState p2 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), ex2);
        IRepository repo2 = new Repository("program2.txt");
        repo2.add(p2);
        Controller controller2 = new Controller(repo2);

        IStatement ex3 = new CompoundStatement(new VariableDeclarationStatement("a",new BoolType()),
                new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                        new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new BoolValue(false))),
                                new CompoundStatement(new IfStatement(new VariableExpression("a"),
                                        new AssignmentStatement("v",new ValueExpression(new IntValue(2))),
                                        new AssignmentStatement("v", new ValueExpression(new IntValue(3)))),
                                        new PrintStatement(new VariableExpression("v"))))));

        ProgramState p3 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), ex3);
        IRepository repo3 = new Repository("program3.txt");
        repo3.add(p3);
        Controller controller3 = new Controller(repo3);

        IStatement ex4 = new CompoundStatement(new VariableDeclarationStatement("v",new IntType()),
                new CompoundStatement(new AssignmentStatement("v",new ValueExpression(new IntValue(2))), new PrintStatement(new VariableExpression("e"))));

        ProgramState p4 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), ex4);
        IRepository repo4 = new Repository("program4.txt");
        repo4.add(p4);
        Controller controller4 = new Controller(repo4);

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

        ProgramState p5 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), ex5);
        IRepository repo5 = new Repository("program5.txt");
        repo5.add(p5);
        Controller controller5 = new Controller(repo5);

        IStatement ex6 = new CompoundStatement(new VariableDeclarationStatement("varf", new StringType()),
            new CompoundStatement(new AssignmentStatement("varf", new ValueExpression(new StringValue("test.in"))),
                    new CompoundStatement(new OpenReadFileStatement(new VariableExpression("varf")),
                            new CompoundStatement(new VariableDeclarationStatement("varc", new IntType()),
                                    new CompoundStatement(new ReadFileStatement(new VariableExpression("varf"), "varc"),
                                            new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                    new CompoundStatement(new ReadFileStatement(new VariableExpression("varf"), "varc"),
                                                            new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                                    new CloseReadFileStatement(new VariableExpression("varf"))))))))));

        ProgramState p6 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), ex6);
        IRepository repo6 = new Repository("program6.txt");
        repo6.add(p6);
        Controller controller6 = new Controller(repo6);

        // int v; v=4; (while (v>0) print(v);v=v-1);print(v)
        IStatement ex7 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(4))),
                    new CompoundStatement(new WhileStatement(new RelationalExpression(new VariableExpression("v"), new ValueExpression(new IntValue(0)), 5),
                            new CompoundStatement(new PrintStatement(new VariableExpression("v")), new AssignmentStatement("v",
                                    new ArithmeticExpression(new VariableExpression("v"), new ValueExpression(new IntValue(1)), 2)))),
                            new PrintStatement(new VariableExpression("v")))));

        ProgramState p7 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), ex7);
        IRepository repo7 = new Repository("program7.txt");
        repo7.add(p7);
        Controller controller7 = new Controller(repo7);

        IStatement ex8 = new CompoundStatement(
                new VariableDeclarationStatement("x", new IntType()),
                    new CompoundStatement(
                        new VariableDeclarationStatement("y", new IntType()),
                            new CompoundStatement(
                                new AssignmentStatement("x", new ValueExpression(new IntValue(4))),
                                    new CompoundStatement(
                                        new AssignmentStatement("y", new ValueExpression(new IntValue(6))),
                                            new PrintStatement(new RelationalExpression(new VariableExpression("x"), new VariableExpression("y"), 1))))));

        ProgramState p8 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), ex8);
        IRepository repo8 = new Repository("program8.txt");
        repo8.add(p8);
        Controller controller8 = new Controller(repo8);


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

        ProgramState p9 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), ex9);
        IRepository repo9 = new Repository("program9.txt");
        repo9.add(p9);
        Controller controller9 = new Controller(repo9);

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

        ProgramState p10 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), ex10);
        IRepository repo10 = new Repository("program10.txt");
        repo10.add(p10);
        Controller controller10 = new Controller(repo10);

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

        ProgramState p11 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), ex11);
        IRepository repo11 = new Repository("program11.txt");
        repo11.add(p11);
        Controller controller11 = new Controller(repo11);

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

        try {
            ex12.typeCheck(new MyDictionary<String, IType>());
        }catch(MyException e){
            System.out.println(e.getMessage());
        }
        ProgramState p12 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), ex12);
        IRepository repo12 = new Repository("program12.txt");
        repo12.add(p12);
        Controller controller12 = new Controller(repo12);


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

        try {
            ex13.typeCheck(new MyDictionary<String, IType>());
        }catch(MyException e){
            System.out.println(e.getMessage());
        }
        ProgramState p13 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), ex13);
        IRepository repo13 = new Repository("program13.txt");
        repo13.add(p13);
        Controller controller13 = new Controller(repo13);

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

        try {
            ex14.typeCheck(new MyDictionary<String, IType>());
        }catch(MyException e){
            System.out.println(e.getMessage());
        }
        ProgramState p14 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), ex14);
        IRepository repo14 = new Repository("program14.txt");
        repo14.add(p14);
        Controller controller14 = new Controller(repo14);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExampleCommand("1", ex1.toString(), controller1));
        menu.addCommand(new RunExampleCommand("2", ex2.toString(), controller2));
        menu.addCommand(new RunExampleCommand("3", ex3.toString(), controller3));
        menu.addCommand(new RunExampleCommand("4", ex4.toString(), controller4));
        menu.addCommand(new RunExampleCommand("5", ex5.toString(), controller5));
        menu.addCommand(new RunExampleCommand("6", ex6.toString(), controller6));
        menu.addCommand(new RunExampleCommand("7", ex7.toString(), controller7));
        menu.addCommand(new RunExampleCommand("8", ex8.toString(), controller8));
        menu.addCommand(new RunExampleCommand("9", ex9.toString(), controller9));
        menu.addCommand(new RunExampleCommand("10", ex10.toString(), controller10));
        menu.addCommand(new RunExampleCommand("11", ex11.toString(), controller11));
        menu.addCommand(new RunExampleCommand("12", ex12.toString(), controller12));
        menu.addCommand(new RunExampleCommand("13", ex13.toString(), controller13));
        menu.addCommand(new RunExampleCommand("14", ex14.toString(), controller14));
        menu.show();*/
    }
}
