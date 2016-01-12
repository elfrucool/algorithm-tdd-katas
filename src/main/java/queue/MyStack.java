package queue;

import java.util.NoSuchElementException;

public class MyStack<T> {
    private Node<T> currentNode;

    public boolean isEmpty() {
        return currentNode == null;
    }

    public T pop() {
        if (isEmpty())
            throw new NoSuchElementException();

        T value = currentNode.getValue();
        currentNode = currentNode.getPrevious();

        return value;
    }

    public void push(T value) {
        currentNode = new Node<>(value, currentNode);
    }

    private static class Node<T> {
        private final T value;
        private final Node<T> previous;

        public Node(T value, Node<T> previous) {
            this.value = value;
            this.previous = previous;
        }

        public T getValue() {
            return value;
        }

        public Node<T> getPrevious() {
            return previous;
        }
    }
}
