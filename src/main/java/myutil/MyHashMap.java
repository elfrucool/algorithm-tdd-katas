package myutil;

public class MyHashMap<K, V> implements MyMap<K, V> {
    private K key;
    private V value;
    private int size = 0;

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void put(K key, V value) {
        this.key = key;
        this.value = value;
        size = 1;
    }

    @Override
    public boolean containsKey(K key) {
        return !isEmpty() && (key == null ? this.key == null : key.equals(this.key));
    }

    @Override
    public V get(K key) {
        if (!containsKey(key))
            return null;
        return value;
    }

    @Override
    public V remove(K key) {
        if (!containsKey(key))
            return null;

        V value = this.value;

        this.value = null;
        this.key = null;
        size = 0;

        return value;
    }

    @Override
    public int size() {
        return size;
    }
}
