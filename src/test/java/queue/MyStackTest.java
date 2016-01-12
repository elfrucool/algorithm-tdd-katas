package queue;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class MyStackTest {

    private MyStack<String> stack;

    @Before
    public void setUp() throws Exception {
        stack = new MyStack<>();
    }

    @Test
    public void newStackIsEmpty() {
        assertTrue(stack.isEmpty());
    }

    @Test(expected = NoSuchElementException.class)
    public void popEmptyStackShouldFail() {
        stack.pop();
    }

    @Test
    public void pushCausesNonEmpty() {
        stack.push("hello");
        assertFalse(stack.isEmpty());
    }

    @Test
    public void pushThenPopReturnsPushedValueAndMakesStackEmpty() {
        stack.push("hello");
        assertEquals("hello", stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    public void twoPushesThenTwoPopsReturnsInLiFoOrder() {
        stack.push("hello");
        stack.push("world");
        assertFalse(stack.isEmpty());
        assertEquals("world", stack.pop());
        assertEquals("hello", stack.pop());
        assertTrue(stack.isEmpty());
    }
}
