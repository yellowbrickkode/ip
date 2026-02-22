package demeter;

/**
 * Represents a simple task without date constraints.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task.
     *
     * @param name   Description of the task.
     * @param isDone Completion status.
     */
    public Todo(String name, boolean isDone) {
        super(name.trim(), isDone);
    }

    /**
     * Returns the formatted display string for a Todo task.
     *
     * @return Formatted task string.
     */
    public String printTask() {
        return super.isDone
                ? "[T][X] " + super.name
                : "[T][ ] " + super.name;
    }

    /**
     * Returns the file storage representation of this task.
     *
     * @return Storage string.
     */
    public String printToFile() {
        return super.isDone
                ? "T | 1 | " + super.name
                : "T | 0 | " + super.name;
    }

    /**
     * Creates a deep copy of this Todo task.
     *
     * @return Copied Todo instance.
     */
    @Override
    public Task copy() {
        return new Todo(this.getName(), this.isDone());
    }
}
