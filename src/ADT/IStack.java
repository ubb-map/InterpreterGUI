package ADT;
import Exception.*;

import java.util.ArrayList;
import java.util.List;

public interface IStack<TElem> {
    public TElem pop();
    public void push(TElem v);
    public TElem peek();
    public boolean is_empty();
    public String to_string();
    public List<TElem> reverse();
    ArrayList<String> getElementsStrings();
}
