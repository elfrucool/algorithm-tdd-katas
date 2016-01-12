package queue;

import java.util.NoSuchElementException;

public class MyQueue<T> {
    private MyStack<T> stackIn = new MyStack<>();
    private MyStack<T> stackOut = new MyStack<>();

    public boolean isEmpty() {
        return stackIn.isEmpty() && stackOut.isEmpty();
    }

    public T dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();

        if (stackOut.isEmpty())
            while (!stackIn.isEmpty())
                stackOut.push(stackIn.pop());

        return stackOut.pop();
    }

    public void enqueue(T value) {
        stackIn.push(value);
    }
}
