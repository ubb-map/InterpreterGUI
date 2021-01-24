package ADT;
import ADT.IDictionary;

import java.util.*;
import java.util.stream.Collectors;

public class MyDictionary<TElem, KValue> implements IDictionary<TElem, KValue> {
    HashMap<TElem, KValue> dict;

    public MyDictionary(){
        dict = new HashMap<>();
    }

    @Override
    public void add(TElem key, KValue element) {
        dict.put(key, element);
    }

    @Override
    public KValue lookup(TElem t){
        return dict.get(t);
    }

    @Override
    public boolean is_defined(TElem t) {
        if(dict.get(t) == null)
            return false;
        return true;
    }

    @Override
    public void setContent(Map<TElem, KValue> newContent) {
        dict = new HashMap<>();
        dict.putAll(newContent);
    }


    @Override
    public IDictionary<TElem, KValue> clone(){
        return (IDictionary<TElem, KValue>) dict;
    }


    @Override
    public Map<TElem, KValue> getContent() {
        return dict.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
    }

    @Override
    public void update(TElem t, KValue v) {
        dict.put(t, v);
    }

    @Override
    public String toString(){
        return dict.toString();
    }

    @Override
    public void remove(TElem t){
        dict.remove(t);
    }

    @Override
    public ArrayList<ArrayList<String>> getElementsStrings() {
        ArrayList<ArrayList<String>> elements = new ArrayList<>();
        for (TElem key : this.dict.keySet()){
            ArrayList<String> list = new ArrayList<>();
            list.add(key.toString());
            list.add(dict.get(key).toString());
            elements.add(list);
        }
        return elements;
    }
}