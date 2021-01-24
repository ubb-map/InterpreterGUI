package ADT;
import ADT.IStack;
import java.util.*;
import Exception.*;
import Statement.IStatement;

public class MyStack<TElem> implements IStack<TElem>{
    Stack<TElem> stack;

    public MyStack(){
        this.stack = new Stack<TElem>();
    }

    @Override
    public TElem pop() {
        return stack.pop();
    }

    @Override
    public void push(TElem v)  {
        stack.push(v);
    }

    @Override
    public TElem peek(){
        return stack.peek();
    }


    @Override
    public boolean is_empty(){
        return stack.isEmpty();
    }

    @Override
    public String to_string(){
        List<TElem> stackToList = new ArrayList(stack);
        Collections.reverse(stackToList);
        String to_return = "";
        for(Object i: stackToList){
            to_return += i.toString();
            to_return += "\n";
        }
        return to_return;
    }

    @Override
    public List<TElem> reverse() {
        List<TElem> list = (List<TElem>)(Arrays.asList(stack));
        Collections.reverse(list);
        return list;
    }

    @Override
    public ArrayList<String> getElementsStrings() {
        ArrayList<String> elements = new ArrayList<>();
        for (var element: this.stack) {
            elements.add(element.toString());
        }
        return elements;
    }

}
