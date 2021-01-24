package ADT;
import ADT.IList;

import java.util.ArrayList;
import java.util.List;

public class MyList<TElem> implements IList<TElem> {
    List<TElem> list;

    public MyList(){
        this.list = new ArrayList<TElem>();
    }

    @Override
    public String toString(){
        String to_return = "\n";
        for(TElem i: list){
            to_return += i.toString();
            to_return += "\n";
        }
        return to_return;
    }

    @Override
    public void add(TElem toPrint) {
        list.add(toPrint);
    }

    @Override
    public ArrayList<String> getElementsStrings() {
        ArrayList<String> elements = new ArrayList<>();
        for (var element: this.list) {
            elements.add(element.toString());
        }
        return elements;
    }
}
