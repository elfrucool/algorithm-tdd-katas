package myutil;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class MyHashMap<K, V> implements MyMap<K, V> {
    protected static final int INITIAL_CAPACITY = 1 << 1; // small value for easy test resizing

    @SuppressWarnings("unchecked")
    List<MyEntry<K, V>>[] table = new List[INITIAL_CAPACITY];

    private int size = 0;

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void put(K key, V value) {
        int index = makeIndex(key);
        if (!containsKey(index, key))
            putNewValue(index, key, value);
        else
            setValue(index, key, value);
    }

    private void setValue(int index, K key, V value) {
        doWithEntry(table[index], key, e -> {
            e.value = value;
            return null;
        }, () -> null);
    }

    private void putNewValue(int index, K key, V value) {
        if (table[index] == null)
            table[index] = new LinkedList<>();
        table[index].add(new MyEntry<>(key, value));
        size++;
    }

    @Override
    public boolean containsKey(K key) {
        return containsKey(makeIndex(key), key);
    }

    private boolean containsKey(int index, K key) {
        return !isEmpty() && table[index] != null && containsKey(table[index], key);
    }

    private boolean containsKey(List<MyEntry<K, V>> entries, K key) {
        return doWithEntry(entries, key, e -> true, () -> false);
    }

    @Override
    public V get(K key) {
        int index = makeIndex(key);
        if (!containsKey(index, key))
            return null;
        return get(index, key);
    }

    private V get(int index, K key) {
        return doWithEntry(table[index], key, e -> e.value, () -> null);
    }

    @Override
    public V remove(K key) {
        int index = makeIndex(key);

        if (!containsKey(index, key))
            return null;

        V value = get(index, key);

        table[index] = null;
        size--;

        return value;
    }

    @Override
    public int size() {
        return size;
    }

    private <R> R doWithEntry(List<MyEntry<K, V>> entries, K key,
                              Function<MyEntry<K, V>, R> whenFound, Supplier<R> whenNotFound) {
        for (MyEntry<K, V> e : entries)
            if (e.isSameKey(key))
                return whenFound.apply(e);
        return whenNotFound.get();
    }

    private int makeIndex(K key) {
        return key == null ? 0 : hash(key);
    }

    private int hash(K key) {
        int hashCode = key.hashCode();
        return (hashCode ^ (hashCode >>> table.length)) & (table.length - 1);
    }

    private static class MyEntry<K, V> {
        public K key;
        private V value;

        public MyEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public boolean isSameKey(K key) {
            return Objects.equals(this.key, key);
        }

        @Override
        public String toString() {
            return "MyNode{" + "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }
}
