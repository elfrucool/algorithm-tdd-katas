package myutil;

public class MyHashMap<K, V> implements MyMap<K, V> {
    private boolean empty = true;

    @Override
    public boolean isEmpty() {
        return empty;
    }

    @Override
    public void put(K key, V value) {
        empty = false;
    }

    @Override
    public boolean containsKey(K key) {
        return !empty;
    }

    @Override
    public V get(K key) {
        return null;
    }
}
