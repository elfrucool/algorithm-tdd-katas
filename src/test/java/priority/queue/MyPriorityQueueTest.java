package priority.queue;

import org.junit.Before;
import org.junit.Test;
import priority.task.Task;

import java.util.*;

import static org.junit.Assert.*;

public class MyPriorityQueueTest {
    // PLAN
    // ====
    // [ok] 1. Build priorizable task
    // [ok] 2. Test priorizable task within java.util.PriorityQueue
    // [ok] 3. use own contract backed by java.util.PriorityQueue
    // [ok] 4. replace backed implementation with own implementation
    // [ok] - we will use a sorted map with keys = tasks, and values = quantity of keys

    public static final Task SAMPLE_TASK = new Task(123L, Task.HIGH, "a task");

    public static final Task HIGH_NEWER_TASK = new Task(2L, Task.HIGH, "high-newer");
    public static final Task LOW_OLDER_TASK = new Task(1L, Task.LOW, "low-older");
    public static final Task LOW_NEWER_TASK = new Task(2L, Task.LOW, "low-newer");
    public static final Task HIGH_OLDER_TASK = new Task(1L, Task.HIGH, "high-older");

    public static final Task[] NOT_PRIORIZED_TASKS = new Task[]{
            LOW_OLDER_TASK,
            HIGH_NEWER_TASK,
            LOW_NEWER_TASK,
            HIGH_OLDER_TASK,
    };

    public static final Task[] PRIORIZED_TASKS = new Task[]{
            HIGH_OLDER_TASK,
            HIGH_NEWER_TASK,
            LOW_OLDER_TASK,
            LOW_NEWER_TASK,
    };

    private MyPriorityQueue<Task> queue;

    @Before
    public void setUp() throws Exception {
        queue = new MyPriorityQueue<>();
    }

    @Test
    public void usingJavaUtilPriorityQueue() {
        Queue<Task> queue = new PriorityQueue<>();

        Collections.addAll(queue, NOT_PRIORIZED_TASKS);

        for (Task task : PRIORIZED_TASKS)
            assertEquals(task, queue.remove());
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
    public void removingEmptyListShouldFail() {
        queue.remove();
    }

    @Test
    public void addItemThenRemovingItShouldReturnItAndMakeTheQueueEmpty() {
        queue.add(SAMPLE_TASK);
        assertEquals(SAMPLE_TASK, queue.remove());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void addingTwoItemsCanRetrieveThemInPriorityOrder() {
        for (Task task: NOT_PRIORIZED_TASKS)
            queue.add(task);

        for (Task task : PRIORIZED_TASKS)
            assertEquals(task, queue.remove());
    }
}
