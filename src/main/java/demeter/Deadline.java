package demeter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate by;

    /**
     * Constructor for Deadline; creates a Deadline task.
     * @param name Name of Deadline.
     * @param isDone Whether Deadline is done.
     * @param by When Deadline is due.
     */
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
     * Returns a string representing an individual Deadline for display to user.
     * The format indicates the task type and its completion status:
     *  - "[D][X] name (by)" if the task is done
     *  - "[D][ ] name (by)" if the task is not done
     *
     * @return a string showing the task type, completion status, name, start date and end date.
     */
    public String printTask() {
        String check = super.isDone ? "[X] " : "[ ] ";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return this.by == null
                ? "[D]" + check + super.name + " (invalid date)"
                : "[D]" + check + super.name + " (" + this.by.format(formatter) + ")";
    }

    /**
     * Returns a string representing an individual Deadline for saving to the hard disk.
     * The format indicates the task type and its completion status:
     *  - "D | 0 | name | by" if the task is done
     *  - "D | 1 | name | by" if the task is not done
     *
     * @return a string showing the task type, completion status, name, start date and end date.
     */
    public String printToFile() {
        return super.isDone
                ? "D | 0 | " + super.name + " | " + this.by
                : "D | 1 | " + super.name + " | " + this.by;
    }
}