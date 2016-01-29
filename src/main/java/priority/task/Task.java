package priority.task;

public class Task implements Comparable<Task> {
    public static final Priority HIGH = Priority.HIGH;
    public static final Priority MED = Priority.MED;
    public static final Priority LOW = Priority.LOW;
    private long createdTimestamp;
    private Priority priority;
    private String description;

    public Task(long createdTimestamp, Task.Priority priority, String description) {
        if (priority == null)
            throw new NullPointerException();

        this.createdTimestamp = createdTimestamp;
        this.priority = priority;
        this.description = description;
    }

    public long getCreatedTimestamp() {
        return createdTimestamp;
    }

    public Priority getPriority() {
        return priority;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "{" +
                createdTimestamp +
                ", " + priority +
                ", " + description +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        return createdTimestamp == task.createdTimestamp && priority == task.priority;

    }

    @Override
    public int hashCode() {
        int result = (int) (createdTimestamp ^ (createdTimestamp >>> 32));
        result = 31 * result + (priority != null ? priority.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Task o) {
        int priorityComparison = o.getPriority().compareTo(priority);
        if (priorityComparison == 0)
            return Long.compare(o.getCreatedTimestamp(), createdTimestamp);
        return priorityComparison;
    }

    private enum Priority {LOW, MED, HIGH}
}
