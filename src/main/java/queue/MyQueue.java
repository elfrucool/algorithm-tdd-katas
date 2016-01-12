package queue;

public class MyQueue<T> {
    private MyStack<T> inputStack = new MyStack<>();
    private MyStack<T> outputStack = new MyStack<>();

    public boolean isEmpty() {
        return inputStack.isEmpty() && outputStack.isEmpty();
    }

    public T dequeue() {
        if (outputStack.isEmpty())
            while (!inputStack.isEmpty())
                outputStack.push(inputStack.pop());
        return outputStack.pop();
    }

    public void enqueue(T value) {
        inputStack.push(value);
    }
}
