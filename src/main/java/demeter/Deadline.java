package demeter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
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

    public String printToFile() {
        return super.isDone
                ? "D | 0 | " + super.name + " | " + this.by
                : "D | 1 | " + super.name + " | " + this.by;
    }
}