package queue;

import java.util.Deque;
import java.util.LinkedList;

public class MyQueue<T> {
    private Deque<T> inputStack = new LinkedList<>();
    private Deque<T> outputStack = new LinkedList<>();

    public boolean isEmpty() {
        return inputStack.isEmpty() && outputStack.isEmpty();
    }

    public T pull() {
        if (outputStack.isEmpty())
            while (!inputStack.isEmpty())
                outputStack.push(inputStack.pop());

        return outputStack.pop();
    }

    public void push(T value) {
        inputStack.push(value);
    }
}
