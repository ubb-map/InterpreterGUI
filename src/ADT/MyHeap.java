package ADT;

import Value.IValue;
import Value.IntValue;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MyHeap<V> extends MyDictionary<Integer, V> implements IHeap<V>  {
    private int freeAddresses = 0;

    @Override
    public int add(V value) {
        add(++freeAddresses, value);
        return freeAddresses;
    }
}
