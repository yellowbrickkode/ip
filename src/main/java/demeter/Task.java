package demeter;

/**
 * Represents an abstract task in the task list.
 * Contains common properties shared by all task types.
 */
public abstract class Task {

    /** Name of the task. */
    protected String name;

    /** Whether the task is marked as done. */
    protected boolean isDone;

    /** Static counter used to assign unique IDs. */
    public static int idx = 0;

    /** Unique ID for each task instance. */
    private int id;

    /**
     * Constructs a Task with name and completion status.
     *
     * @param name Description of task.
     * @param done Completion state.
     */
    public Task(String name, boolean done) {
        assert !name.isEmpty() : "Task name should not be empty";
        this.name = name;
        this.isDone = done;
        this.id = idx ++;
    }


    /**
     * Returns the formatted display string of the task.
     * Implemented by subclasses.
     */
    public String printTask() {
        return "";
    }

    /** @return task name */
    public String getName() { return this.name; }

    /** @return unique task ID */
    public int getId() {
        return this.id;
    }

    /** Marks the task as completed. */
    public void mark() {
        this.isDone = true;
    }

    /** Marks the task as not completed. */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns string representation for saving to file.
     * Implemented by subclasses.
     */
    public String printToFile() {
        return null;
    }

    /** @return true if task is done */
    public boolean isDone() { return this.isDone; }

    /**
     * Creates a deep copy of the task.
     *
     * @return copied task instance.
     */
    public abstract Task copy();
}