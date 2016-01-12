package queue;

import java.util.NoSuchElementException;

public class MyStack<T> {
    private MyNode<T> top;
    
    public boolean isEmpty() {
        return top == null;
    }

    public T pop() {
        if (isEmpty())
            throw new NoSuchElementException();

        T value = top.value;
        top = top.previous;

        return value;
    }

    public void push(T value) {
        top = new MyNode<>(value, top);
    }

    private static class MyNode<T> {
        public T value;
        public MyNode<T> previous;

        public MyNode(T value, MyNode<T> previous) {
            this.value = value;
            this.previous = previous;
        }
    }
}
