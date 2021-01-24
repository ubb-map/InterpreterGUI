package ADT;

import Value.IValue;

import java.util.ArrayList;
import java.util.Map;

public interface IHeap<V> extends IDictionary<Integer, V> {
    int add(V value);
}
