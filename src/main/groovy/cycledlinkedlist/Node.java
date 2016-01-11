package cycledlinkedlist;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.StreamSupport;

public class Node<T> implements Iterable<Node<T>> {
    private T value;
    private Node<T> next;

    public Node() {
    }

    public Node(T value) {
        this.value = value;
    }

    public Node(T value, Node<T> next) {
        this.value = value;
        this.next = next;
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

    @SafeVarargs
    public static <T> Node<T> fromValues(T... values) {
        if (values.length == 0)
            return  null;

        Node<T> node = new Node<>(values[0]);
        Node<T> firstNode = node;

        for (int i = 1; i < values.length; i++) {
            node.setNext(new Node<>(values[i]));
            node = node.getNext();
        }

        return firstNode;
    }

    public Node<T> get(int index) {
        if (index < 0)
            throw new IndexOutOfBoundsException();

        Iterator<Node<T>> iterator = iterator();

        Node<T> node = null;

        while (iterator.hasNext() && index-- >= 0)
            node = iterator.next();

        if (index >= 0)
            throw new IndexOutOfBoundsException();

        return node;
    }

    public Node<T> last() {
        return StreamSupport.stream(spliterator(), false).reduce((n1, n2) -> n2).get();
    }

    public static <T> boolean isCyclic(Node<T> node) {
        if (node.getNext() == null)
            return false;

        Iterator<Node<T>> iterator1 = node.iterator();
        Iterator<Node<T>> iterator2 = node.iterator();

        while (iterator1.hasNext() && iterator2.hasNext()) {
            iterator2.next();
            if (iterator2.hasNext() && iterator1.next() == iterator2.next())
                return true;
        }

        return false;
    }

    private class NodeIterator implements Iterator<Node<T>> {
        private Node<T> cursorNode = Node.this;

        @Override
        public boolean hasNext() {
            return cursorNode != null;
        }

        @Override
        public Node<T> next() {
            if (!hasNext())
                throw new NoSuchElementException();

            Node<T> node = cursorNode;
            cursorNode = cursorNode.next;
            return node;
        }
    }
}
