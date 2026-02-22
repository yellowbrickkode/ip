package demeter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that occurs over a time range.
 */
public class Event extends Task {

    /** Start date of Event. */
    private LocalDate from;

    /** End date of Event. */
    private LocalDate to;

    /**
     * Constructs an Event task.
     *
     * @param name   Description of event.
     * @param isDone Completion status.
     * @param from   Start date string (yyyy-MM-dd).
     * @param to     End date string (yyyy-MM-dd).
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
     * Returns the formatted display string for an Event task.
     *
     * @return Formatted task string.
     */
    public String printTask() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return super.isDone
                ? "[E][X] " + super.name + " (" + this.from.format(formatter) + " - " + this.to.format(formatter) + ")"
                : "[E][ ] " + super.name + " (" + this.from.format(formatter) + " - " + this.to.format(formatter) + ")";
    }

    /**
     * Returns the file storage representation of this task.
     *
     * @return Storage string.
     */
    public String printToFile() {
        return super.isDone
                ? "E | 1 | " + super.name + " | " + this.from + " - " + this.to
                : "E | 0 | " + super.name + " | " + this.from + " - " + this.to;
    }

    /** @return start date */
    public LocalDate getFrom() {
        return this.from;
    }

    /** @return end date */
    public LocalDate getTo() {
        return this.to;
    }

    /**
     * Creates a deep copy of this Event task.
     *
     * @return Copied Event instance.
     */
    @Override
    public Task copy() {
        return new Event(this.getName(), this.isDone(), this.getFrom().toString(), this.getTo().toString());
    }
}