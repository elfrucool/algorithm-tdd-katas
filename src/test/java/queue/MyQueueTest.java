package queue;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class MyQueueTest {
    private MyQueue<String> queue;
    // PLAN
    // ====
    // [ok] 1. implement the case with something that already works (e.g. ArrayDeque used as Queue)
    // [ok] 2. replace that implementation with two already working stacks (e.g. 2 ArrayDeque's used as Stack)
    // [ok] 3. replace the stacks with our own implementation
    // [ok] 3.1. use a wrapper (to make things easy)
    // [ok] 3.2. replace wrapper with own implementation


    @Before
    public void setUp() throws Exception {
        queue = new MyQueue<>();
    }

    @Test
    public void newQueueIsEmpty() {
        assertTrue(queue.isEmpty());
    }

    @Test(expected = NoSuchElementException.class)
    public void dequeuingEmptyQueueShouldFail() {
        queue.dequeue();
    }

    @Test
    public void enqueuingMakesQueueNonEmpty() {
        queue.enqueue("a");
        assertFalse(queue.isEmpty());
    }

    @Test
    public void givenSingleEnqueuedValueWhenDequeuedShouldReturnTheValueAndBecomeEmpty() {
        queue.enqueue("a");
        assertEquals("a", queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void multipleEqueuedValuesShouldBeDequeuedInSameOrder() {
        queue.enqueue("a");
        queue.enqueue("b");
        assertFalse(queue.isEmpty());
        assertEquals("a", queue.dequeue());
        assertEquals("b", queue.dequeue());
        assertTrue(queue.isEmpty());
    }
}
