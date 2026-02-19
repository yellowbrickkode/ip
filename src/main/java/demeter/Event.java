package demeter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate from;
    private LocalDate to;
    public Event(String name, boolean done, String from, String to) {
        super(name.trim(), done);
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
        String check = super.done ? "[X] " : "[ ] ";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return "[E]" + check + super.name + " (" + this.from.format(formatter) + " - " + this.to.format(formatter) + ")";
    }

    public String printToFile() {
        return super.done
                ? "E | 0 | " + super.name + " | " + this.from + " - " + this.to
                : "E | 1 | " + super.name + " | " + this.from + " - " + this.to;
    }
}