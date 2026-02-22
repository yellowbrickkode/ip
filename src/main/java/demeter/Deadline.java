package demeter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    /** Due date of Deadline. */
    private LocalDate by;

    public Deadline(String name, boolean isDone, String by) {
        super(name.trim(), isDone);
        try {
            this.by = LocalDate.parse(by.trim());
        } catch (Exception e) {
            this.by = null;
            System.out.println("Invalid date format! Use yyyy-MM-dd");
        }
    }
    /**
     * Returns the formatted display string for a Deadline task.
     *
     * @return Formatted task string.
     */
    public String printTask() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return this.by == null
                ? super.isDone
                ? "[D][X] " + super.name + " (invalid date)"
                : "[D][ ] " + super.name + " (invalid date)"
                : super.isDone
                ? "[D][X] " + super.name + " (" + this.by.format(formatter) + ")"
                : "[D][ ] " + super.name + " (" + this.by.format(formatter) + ")";
    }

    /**
     * Returns the file storage representation of this task.
     *
     * @return Storage string.
     */
    public String printToFile() {
        return super.isDone
                ? "D | 1 | " + super.name + " | " + this.by
                : "D | 0 | " + super.name + " | " + this.by;
    }

    /** @return due date */
    public LocalDate getBy() {
        return this.by;
    }

    /**
     * Creates a deep copy of this Deadline task.
     *
     * @return Copied Deadline instance.
     */
    @Override
    public Task copy() {
        return new Deadline(this.getName(), this.isDone(), this.getBy().toString());
    }
}