package priority.queue;

import org.junit.Before;
import org.junit.Test;
import priority.task.Task;

import java.util.*;

import static org.junit.Assert.*;

public class MyPriorityQueueTest {
    public static final Task SAMPLE_TASK = new Task(123L, Task.HIGH, "a task");

    public static final Task[] SOME_TASKS = new Task[]{
            new Task(1L, Task.LOW, "low-newer"),
            new Task(1L, Task.HIGH, "high-newer"),
            new Task(2L, Task.HIGH, "high-older"),
            new Task(2L, Task.LOW, "low-older"),
    };

    private MyPriorityQueue<Task> queue;

    @Before
    public void setUp() throws Exception {
        queue = new MyPriorityQueue<>();
    }

    // PLAN
    // ====
    // [ok] 1. Build priorizable task
    // [ok] 2. Test priorizable task within java.util.PriorityQueue
    // 3. use own contract backed by java.util.PriorityQueue
    // 4. replace backed implementation with own implementation
    @Test
    public void usingJavaUtilPriorityQueue() {

        Queue<Task> queue = new PriorityQueue<>();

        Collections.addAll(queue, SOME_TASKS);

        assertEquals(new Task(2L, Task.HIGH, "high-older"), queue.remove());
        assertEquals(new Task(1L, Task.HIGH, "high-newer"), queue.remove());
        assertEquals(new Task(2L, Task.LOW, "low-older"), queue.remove());
        assertEquals(new Task(1L, Task.LOW, "low-newer"), queue.remove());
    }

    @Test
    public void newQueueIsEmpty() {
        assertTrue(queue.isEmpty());
    }

    @Test
    public void addItemThenNotEmpty() {
        queue.add(SAMPLE_TASK);
        assertFalse(queue.isEmpty());
    }

    @Test(expected = NoSuchElementException.class)
    public void deletingEmptyListShouldFail() {
        queue.delete();
    }

    @Test
    public void addItemThenDeletingItShouldReturnItAndMakeTheQueueEmpty() {
        queue.add(SAMPLE_TASK);
        assertEquals(SAMPLE_TASK, queue.delete());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void addingTwoItemsCanRetrieveThemInPriorityOrder() {
    }
}
