package demeter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDate from;
    private LocalDate to;

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

    public String printTask() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return super.isDone
                ? "[E][X] " + super.name + " (" + this.from.format(formatter) + " - " + this.to.format(formatter) + ")"
                : "[E][ ] " + super.name + " (" + this.from.format(formatter) + " - " + this.to.format(formatter) + ")";
    }

    public String printToFile() {
        return super.isDone
                ? "E | 0 | " + super.name + " | " + this.from + " - " + this.to
                : "E | 1 | " + super.name + " | " + this.from + " - " + this.to;
    }
}