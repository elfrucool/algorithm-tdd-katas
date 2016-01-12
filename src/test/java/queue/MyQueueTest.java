package queue;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MyQueueTest {
    private MyQueue<String> queue;
    // PLAN
    // ====
    // [ok] 0. enqueue -> push, dequeue -> pull
    // [ok] 1. implement using known implementation
    // [ok] 2. implement using two stacks, each stack is a known implementation
    // [ok] 3. implement using own stack implementation
    // [ok] 3.1. create contract and use known stack implementation behind
    // [ok] 3.2. replace known implementation with own implementation

    @Before
    public void setUp() throws Exception {
        queue = new MyQueue<>();
    }

    @Test
    public void newQueueIsEmpty() {
        assertTrue(queue.isEmpty());
    }

    @Test(expected = NoSuchElementException.class)
    public void pullingEmtyQueueShouldFail() {
        queue.pull();
    }

    @Test
    public void pushEmptyQueueMakesItNonEmpty() {
        queue.push("hello");
        assertFalse(queue.isEmpty());
    }

    @Test
    public void pullSingleItemQueueMakesItEmpty() {
        queue.push("hello");
        queue.pull();
        assertTrue(queue.isEmpty());
    }

    @Test
    public void pushThenPullReturnsValue() {
        queue.push("hello");
        assertEquals("hello", queue.pull());
    }

    @Test
    public void pushingTwoValuesThenPullingShouldReturnThemInFiFoOrder() {
        queue.push("hello");
        queue.push("world");
        assertEquals("hello", queue.pull());
        assertEquals("world", queue.pull());
    }
}
