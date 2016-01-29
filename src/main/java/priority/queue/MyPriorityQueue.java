package priority.queue;

import java.util.NoSuchElementException;

public class MyPriorityQueue<T> {
    private T item;

    public boolean isEmpty() {
        return item == null;
    }

    public void add(T item) {
        this.item = item;
    }

    public T delete() {
        if (isEmpty())
            throw new NoSuchElementException();
        T item = this.item;
        this.item = null;
        return item;
    }
}
