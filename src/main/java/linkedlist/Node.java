package linkedlist;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class Node<T> implements Iterable<Node<T>> {
    private T value;
    private Node<T> next;

    public Node() {
    }

    public Node(T value) {
        setValue(value);
    }

    public Node(T value, Node<T> next) {
        setValue(value);
        setNext(next);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node<?> node = (Node<?>) o;
        return Objects.equals(value, node.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public Node<T> getNext() {
        return next;
    }

    @Override
    public Iterator<Node<T>> iterator() {
        return new NodeIterator();
    }

    public Node<T> get(int index) {
        if (index < 0)
            throw new IndexOutOfBoundsException();

        Iterator<Node<T>> iterator = this.iterator();

        while (--index >= 0) {
            if (!iterator.hasNext())
                throw new IndexOutOfBoundsException();
            iterator.next();
        }

        if (!iterator.hasNext())
            throw new IndexOutOfBoundsException();
        return iterator.next();
    }

    public int size() {
        int size = 0;
        for (Node<T> ignored : this)
            size++;
        return size;
    }

    public boolean isCircular() {
        Iterator<Node<T>> slow = iterator();
        Iterator<Node<T>> fast = iterator();

        while (slow.hasNext() && fast.hasNext()) {
            fast.next();
            Node<T> slowNode = slow.next();
            if (isNextCircular(fast, slowNode) || isNextCircular(fast, slowNode))
                return true;
        }

        return false;
    }

    protected boolean isNextCircular(Iterator<Node<T>> fast, Node<T> slowNode) {
        if (fast.hasNext()) {
            Node<T> fastNode = fast.next();
            if (slowNode == fastNode)
                return true;
        }
        return false;
    }

    private class NodeIterator implements Iterator<Node<T>> {
        private Node<T> current = Node.this;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Node<T> next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Node<T> node = current;
            current = current.next;
            return node;
        }
    }
}
