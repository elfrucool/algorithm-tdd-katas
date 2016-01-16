package myutil;

public interface MyMap<K, V> {
    boolean isEmpty();

    void put(K key, V value);

    boolean containsKey(K key);

    V get(K key);

    V remove(K key);

    int size();
}
