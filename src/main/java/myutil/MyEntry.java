package myutil;

public class MyEntry<K extends Comparable<K>, V> {
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
        if (entry == null)
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
        int comparison = key.compareTo(this.key);

        if (comparison == 0)
            return this;

        if (comparison < 0)
            return left != null ? left.find(key) : null;

        return right != null ? right.find(key) : null;
    }

    public MyEntry<K, V> remove() {
        MyEntry<K, V> replacement = getReplacementAfterRemove();

        MyEntry<K, V> parent = removeFromParent();
        MyEntry<K, V> left = removeLeft();
        MyEntry<K, V> right = removeRight();

        if (left != null)
            left.insert(right);

        return replacement != null ? replacement : parent;
    }

    private MyEntry<K, V> getReplacementAfterRemove() {
        return left != null ? left : right != null ? right : null;
    }

    private MyEntry<K, V> removeFromParent() {
        MyEntry<K, V> parent = this.parent;
        if (parent != null) {
            if (this == parent.getLeft())
                parent.removeLeft();
            else
                parent.removeRight();
            return parent;
        }
        return null;
    }
}
