package ProgramState;
import ADT.*;
import Statement.IStatement;
import com.sun.jdi.Value;
import Value.*;
import Exception.*;
import java.io.BufferedReader;
import java.io.File;
import java.nio.Buffer;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;

public class ProgramState {
    IStack<IStatement> exeStack;
    //ILockTable<Integer, Integer> LockTable;
    IDictionary<String, IValue> symTable;
    IList<IValue> out;
    IStatement originalProgram;
    IDictionary<StringValue, BufferedReader> FileTable;
    IHeap<IValue> heap;
    private final int id;
    private static final AtomicInteger programStatesCount = new AtomicInteger(0);

    private static synchronized int getNewProgramId() {
        return programStatesCount.addAndGet(1);
    }

    public ProgramState(IStack<IStatement> stack, IDictionary<String, IValue> dict, IList<IValue> ot,
                        IDictionary<StringValue, BufferedReader> file_table, IHeap<IValue> heap, IStatement program) {
        this.exeStack = stack;
        this.symTable = dict;
        this.out = ot;
        //this.LockTable = LockTable;
        this.originalProgram = program;
        this.exeStack.push(program);
        this.FileTable = file_table;
        this.heap = heap;
        id = getNewProgramId();
    }

    public int getId(){
        return id;
    }

    public String toString(){
        return "ID: " + id + "\n" +
                "The execution stack is: " + "\n" + exeStack.to_string() + "\n" +
                "The symbols table is: " + symTable.toString() + "\n" + "The output is: " + out.toString() + '\n' +
                "The file table is: " + FileTable.toString() + "\n" +
                "The heap is: " + heap.toString() + "\n" +
                "---------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                "\n\n";
    }

    public boolean is_not_completed(){
        return !this.exeStack.is_empty();
    }

    public ProgramState oneStepExecution() throws Exception {
        if(exeStack.is_empty())
            throw new MyException("prgstate stack is empty");
        IStatement  crtStmt = exeStack.pop();
        return crtStmt.execute(this);
    }

    public String display(){
        return exeStack.toString() + "\n";
    }

    public IHeap<IValue> get_heap() {
        return this.heap;
    }

    public IStack<IStatement> get_exeStack(){
        return this.exeStack;
    }

    public IDictionary<String, IValue> get_symTable(){
        return this.symTable;
    }

    public IList<IValue> get_out(){
        return this.out;
    }


    public IDictionary<StringValue, BufferedReader> get_fileTable(){
        return this.FileTable;
    }

    public void set_stack(IStack<IStatement> st){
        this.exeStack = st;
    }

    public void set_symTable(IDictionary<String, IValue> dict){
        this.symTable = dict;
    }

    public void set_out(IList<IValue> list){
        this.out = list;
    }
}

