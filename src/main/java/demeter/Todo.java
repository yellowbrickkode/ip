package demeter;

public class Todo extends Task {

    /**
     * Constructor for Todo; creates a Todo task.
     * @param name Description of Todo.
     * @param isDone Whether Todo is done.
     */
    public Todo(String name, boolean isDone) {
        super(name.trim(), isDone);
    }

    /**
     * Returns a string representing an individual Todo for display to user.
     * The format indicates the task type and its completion status:
     *  - "[T][X] name" if the task is done
     *  - "[T][ ] name" if the task is not done
     *
     * @return a string showing the task type, completion status, and name
     */
    public String printTask() {
        return super.isDone
                ? "[T][X] " + super.name
                : "[T][ ] " + super.name;
    }

    /**
     * Returns a string representing an individual Todo for saving to the hard disk.
     * The format indicates the task type and its completion status:
     *  - "T | 0 | name" if the task is done
     *  - "T | 1 | name" if the task is not done
     *
     * @return a string showing the task type, completion status, and name
     */
    public String printToFile() {
        return super.isDone
                ? "T | 0 | " + super.name
                : "T | 1 | " + super.name;
    }
}
