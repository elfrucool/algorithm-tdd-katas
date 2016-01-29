package priority.queue;

import java.util.SortedMap;
import java.util.TreeMap;

public class MyPriorityQueue<T> {
    private SortedMap<T, Long> itemsMap = new TreeMap<>();

    public boolean isEmpty() {
        return itemsMap.isEmpty();
    }

    public void add(T item) {
        if (!itemsMap.containsKey(item))
            itemsMap.put(item, 0L);
        itemsMap.put(item, itemsMap.get(item) + 1L);
    }

    public T remove() {
        T first = itemsMap.firstKey();
        decrementItemAt(first);
        return first;
    }

    protected void decrementItemAt(T key) {
        long quantity = itemsMap.get(key);
        if (--quantity == 0)
            itemsMap.remove(key);
        else
            itemsMap.put(key, quantity);
    }
}
