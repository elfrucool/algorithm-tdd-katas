package queue;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class MyQueueTest {
    // PLAN
    // ====
    // [ok] 1. using known implementation
    // [ok] 2. using two stacks each one with known implementation
    // [ok] 3. implement stack
    // [ok] 3.1. wrap the two stacks in own contract
    // [ok] 3.2. replace stacks known implementation with own implementation
    private MyQueue<String> queue;

    @Before
    public void setUp() throws Exception {
        queue = new MyQueue<>();
    }

    @Test
    public void newQueueIsEmpty() {
        assertTrue(queue.isEmpty());
    }

    @Test(expected = NoSuchElementException.class)
    public void dequeueOnEmptyQueueShouldFail() {
        queue.dequeue();
    }

    @Test
    public void enqueueMakesQueueNonEmpty() {
        queue.enqueue("hello");
        assertFalse(queue.isEmpty());
    }

    @Test
    public void enqueueThenDequeueShouldBeEmpty() {
        queue.enqueue("hello");
        queue.dequeue();
        assertTrue(queue.isEmpty());
    }

    @Test
    public void enqueueThenDequeueShouldReturnValue() {
        queue.enqueue("hello");
        assertEquals("hello", queue.dequeue());
    }

    @Test
    public void twoEnqueueThenTwoDequeueReturnValuesInFiFoOrder() {
        queue.enqueue("hello");
        queue.enqueue("world");
        assertEquals("hello", queue.dequeue());
        assertEquals("world", queue.dequeue());
    }
}
