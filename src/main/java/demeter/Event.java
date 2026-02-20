package demeter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    /**
     * Constructor for Event; creates an Event task.
     * @param name Description of Event.
     * @param isDone Whether Event is done.
     * @param from When Event starts.
     * @param to When Event ends.
     */
    public Event(String name, boolean isDone, String from, String to) {
        super(name.trim(), isDone);
        try {
            this.from = LocalDate.parse(from.trim());
            this.to = LocalDate.parse(to.trim());
        } catch (Exception e) {
            this.from = null;
            this.to = null;
            System.out.println("Invalid date format! Use yyyy-MM-dd");
        }

    }

    /**
     * Returns a string representing an individual Event for display to user.
     * The format indicates the task type and its completion status:
     *  - "[E][X] name (from - to)" if the task is done
     *  - "[E][ ] name (from - to)" if the task is not done
     *
     * @return a string showing the task type, completion status, name, start date and end date.
     */
    public String printTask() {
        String check = super.isDone ? "[X] " : "[ ] ";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return "[E]" + check + super.name + " (" + this.from.format(formatter) + " - " + this.to.format(formatter) + ")";
    }

    /**
     * Returns a string representing an individual Event for saving to the hard disk.
     * The format indicates the task type and its completion status:
     *  - "E | 0 | name | from - to" if the task is done
     *  - "E | 1 | name | from - to" if the task is not done
     *
     * @return a string showing the task type, completion status, name, start date and end date.
     */
    public String printToFile() {
        return super.isDone
                ? "E | 0 | " + super.name + " | " + this.from + " - " + this.to
                : "E | 1 | " + super.name + " | " + this.from + " - " + this.to;
    }
}