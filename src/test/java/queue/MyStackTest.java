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
    public void newStackShouldBeEmpty() {
        assertTrue(stack.isEmpty());
    }

    @Test(expected = NoSuchElementException.class)
    public void popOnEmptyStackShouldFail() {
        stack.pop();
    }

    @Test
    public void pushMakesStackNonEmpty() {
        stack.push("hello");
        assertFalse(stack.isEmpty());
    }

    @Test
    public void pushThenPopMakesStackEmpty() {
        stack.push("hello");
        stack.pop();
        assertTrue(stack.isEmpty());
    }

    @Test
    public void pushThenPopReturnsValue() {
        stack.push("hello");
        assertEquals("hello", stack.pop());
    }

    @Test
    public void pushTwoValuesThenPopThemRetursValuesInLiFoOrder() {
        stack.push("hello");
        stack.push("world");
        assertEquals("world", stack.pop());
        assertEquals("hello", stack.pop());
    }
}
