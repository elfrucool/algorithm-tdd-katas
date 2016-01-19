package myutil;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyEntry<K extends Comparable<K>, V> implements Iterable<MyEntry<K, V>> {
    public enum ChildType {LEFT, RIGHT, NONE}

    private K key;
    private V value;

    private MyEntry<K, V> parent;
    private MyEntry<K, V> left;
    private MyEntry<K, V> right;

    public MyEntry() {
    }

    public MyEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "MyEntry{" +
                "key=" + key + "," +
                "value=" + value + "," +
                "left=" + (left != null ? left : null) + "," +
                "right=" + (right != null ? right : null) +
                '}';
    }

    public void setKey(K key) {
        this.key = key;
    }

    public K getKey() {
        return key;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public V getValue() {
        return value;
    }

    public void setParent(MyEntry<K, V> parent) {
        this.parent = parent;
    }

    public MyEntry<K, V> getParent() {
        return parent;
    }

    public void setLeft(MyEntry<K, V> left) {
        this.left = left;
    }

    public MyEntry<K, V> getLeft() {
        return left;
    }

    public void setRight(MyEntry<K, V> right) {
        this.right = right;
    }

    public MyEntry<K, V> getRight() {
        return right;
    }

    public void insertLeft(MyEntry<K, V> left) {
        this.left = left;
        left.setParent(this);
    }

    public void insertRight(MyEntry<K, V> right) {
        this.right = right;
        right.setParent(this);
    }

    public MyEntry<K, V> removeLeft() {
        MyEntry<K, V> left = this.left;

        if (left != null)
            left.setParent(null);

        this.left = null;

        return left;
    }

    public MyEntry<K, V> removeRight() {
        MyEntry<K, V> right = this.right;

        if (right != null)
            right.setParent(null);

        this.right = null;

        return right;
    }

    public void insert(MyEntry<K, V> entry) {
        insertWithoutRebalancing(entry);
    }

    protected void insertWithoutRebalancing(MyEntry<K, V> entry) {
        if (entry == null || this == entry)
            return;

        if (entry.getKey().compareTo(key) < 0)
            insertLeftOrInLeftInsert(entry);

        else if (entry.getKey().compareTo(key) > 0)
            insertRightOrInRightInsert(entry);

        else {
            value = entry.getValue();
            insert(entry.getLeft());
            insert(entry.getRight());
        }
    }

    private void insertRightOrInRightInsert(MyEntry<K, V> entry) {
        if (right == null)
            insertRight(entry);
        else
            right.insert(entry);
    }

    private void insertLeftOrInLeftInsert(MyEntry<K, V> entry) {
        if (left == null)
            insertLeft(entry);
        else
            left.insert(entry);
    }

    public MyEntry<K, V> getRoot() {
        if (parent == null)
            return this;
        return parent.getRoot();
    }

    public MyEntry<K, V> getSmallest() {
        if (left == null)
            return this;
        return left.getSmallest();
    }

    public MyEntry<K, V> getBiggest() {
        if (right == null)
            return this;
        return right.getBiggest();
    }

    public MyEntry<K, V> find(K key) {
        return getRoot().findFromHere(key);
    }

    // this method does not look for root
    private MyEntry<K, V> findFromHere(K key) {
        int comparison = key.compareTo(this.key);

        if (comparison == 0)
            return this;

        if (comparison < 0)
            return left != null ? left.findFromHere(key) : null;

        return right != null ? right.findFromHere(key) : null;
    }

    public ChildType getChildType() {
        return parent == null ? ChildType.NONE : parent.getLeft() == this ? ChildType.LEFT : ChildType.RIGHT;
    }

    public MyEntry<K, V> remove(K key) {
        MyEntry<K, V> found = find(key);
        return found != null ? found.remove() : null;
    }

    public MyEntry<K, V> remove() {
        MyEntry<K, V> replacement = getReplacementAfterRemove();
        MyEntry<K, V> parent = removeFromParent();
        MyEntry<K, V> left = removeLeft();
        MyEntry<K, V> right = removeRight();

        if (replacement != null) {
            if (parent != null)
                parent.insert(replacement);

            replacement.insert(left);
            replacement.insert(right);

            return replacement;
        }

        return parent;
    }

    private MyEntry<K, V> getReplacementAfterRemove() {
        MyEntry<K, V> replacement = left != null ? left.getBiggest() : right != null ? right : null;
        if (replacement != null)
            replacement.removeFromParent();
        return replacement;
    }

    private MyEntry<K, V> removeFromParent() {
        ChildType childType = getChildType();

        MyEntry<K, V> parent = this.parent;

        if (childType == ChildType.LEFT)
            parent.removeLeft();
        else if (childType == ChildType.RIGHT)
            parent.removeRight();

        return parent;
    }

    private MyEntry<K, V> getSuccessor() {
        MyEntry<K, V> cursor = this;

        if (cursor.getRight() != null)
            return cursor.getRight().getSmallest();

        if (cursor.getChildType() != ChildType.RIGHT)
            return cursor.getParent();

        while (cursor.getChildType() == ChildType.RIGHT)
            cursor = cursor.getParent();

        return cursor.getParent();
    }

    @Override
    public Iterator<MyEntry<K, V>> iterator() {
        return new MyEntryIterator();
    }

    private class MyEntryIterator implements Iterator<MyEntry<K, V>> {
        private MyEntry<K, V> cursor = MyEntry.this.getSmallest();

        @Override
        public boolean hasNext() {
            return cursor != null;
        }

        @Override
        public MyEntry<K, V> next() {
            if (!hasNext())
                throw new NoSuchElementException();

            MyEntry<K, V> actual = cursor;
            cursor = cursor.getSuccessor();

            return actual;
        }

    }
}
