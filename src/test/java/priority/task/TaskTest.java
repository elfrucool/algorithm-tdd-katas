package priority.task;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TaskTest {

    public static final String TASK_DESCRIPTION = "task description";
    public static final long CREATED_TIMESTAMP = 234L;
    private Task task;

    @Before
    public void setUp() throws Exception {
        task = new Task(CREATED_TIMESTAMP, Task.HIGH, TASK_DESCRIPTION);
    }

    @Test
    public void hasProperties() {
        assertEquals(CREATED_TIMESTAMP, task.getCreatedTimestamp());
        assertEquals(Task.HIGH, task.getPriority());
        assertEquals(TASK_DESCRIPTION, task.getDescription());
    }

    @Test
    public void hasToString() {
        assertEquals("{234, HIGH, task description}", task.toString());
    }

    @Test(expected = NullPointerException.class)
    public void priorityIsRequired() {
        new Task(CREATED_TIMESTAMP, null, TASK_DESCRIPTION);
    }

    @Test
    public void equalsOnlyConsiderTimestampAndPriority() {
        assertEquals(new Task(CREATED_TIMESTAMP, Task.HIGH, "desc1"), new Task(CREATED_TIMESTAMP, Task.HIGH, "desc2"));
        assertNotEquals(new Task(1L, Task.HIGH, "desc1"), new Task(CREATED_TIMESTAMP, Task.HIGH, "desc2"));
        assertNotEquals(
                new Task(CREATED_TIMESTAMP, Task.MED, "desc1"), new Task(CREATED_TIMESTAMP, Task.HIGH, "desc2"));
    }

    @Test
    public void isComparable() {
        assertTrue("should be comparable", task instanceof Comparable);
    }

    @Test
    public void compareTakesPriorityFirstAndThenCreatedTimestamp() {
        Task medPriority = new Task(CREATED_TIMESTAMP, Task.MED, "whatever");
        Task lowPriority = new Task(CREATED_TIMESTAMP, Task.LOW, "whatever else");
        Task newer = new Task(CREATED_TIMESTAMP - 1, Task.HIGH, "other");
        Task older = new Task(CREATED_TIMESTAMP + 1, Task.MED, "foo");
        // when equals() == true: then compareTo() must be = 0
        assertTrue("T1 = T1", task.compareTo(task) == 0);
        // by priority
        assertTrue("T1 (HIGH) < T2 (MED)", task.compareTo(medPriority) < 0);
        assertTrue("T2 (MED) < T1 (HIGH)", medPriority.compareTo(task) > 0);
        assertTrue("T2 (MED) < T3 (LOW)", medPriority.compareTo(lowPriority) < 0);
        // by timestamp
        assertTrue("T1 < T4 (newer)", task.compareTo(newer) < 0);
        assertTrue("T4 (newer) > T1", newer.compareTo(task) > 0);
        // priority has priority over timestamp
        assertTrue("T1 (HIGH) < T5 (older but MED)", task.compareTo(older) < 0);
    }
}
