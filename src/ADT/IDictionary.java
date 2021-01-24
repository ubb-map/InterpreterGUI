package ADT;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public interface IDictionary<TElem, KValue> {
    void add(TElem key, KValue element);
    public KValue lookup(TElem t);
    public boolean is_defined(TElem t);
    public void update(TElem t, KValue v);
    public String toString();
    public void remove(TElem t);
    Map<TElem,KValue> getContent();
    void setContent(Map<TElem, KValue> newContent);
    ArrayList<ArrayList<String>> getElementsStrings();
    IDictionary<TElem, KValue> clone();
}