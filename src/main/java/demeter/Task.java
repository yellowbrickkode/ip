package demeter;

public class Task {
    protected String name;
    protected boolean isDone;
    public static int idx = 0;
    private int id;

    /**
     * Constructor for Task class; creates a Task.
     * @param name Description of task.
     * @param isDone Whether task has been completed.
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
        this.id = idx ++;
    }

    /**
     * Returns a string representing an individual task for display to user.
     * @return an empty string; to be overridden by printTask() in Todo, Deadline, Event classes.
     */
    public String printTask() {
        return "";
    }

    /**
     * Returns the id of a task.
     * @return id of task.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Marks a task as completed.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks a task as incomplete.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns a string representing an individual task for saving to the hard disk.
     * @return an empty string; to be overridden by printToFile() in Todo, Deadline, Event classes.
     */
    public String printToFile() {
        return null;
    }

    /**
     * Returns whether a task is completed.
     * @return isDone of task.
     */
    public boolean isDone() { return this.isDone; }
}