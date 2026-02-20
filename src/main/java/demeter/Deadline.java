package demeter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate by;
    public Deadline(String name, boolean done, String by) {
        super(name.trim(), done);
        try {
            this.by = LocalDate.parse(by.trim());
        } catch (Exception e) {
            this.by = null;
            System.out.println("Invalid date format! Use yyyy-MM-dd");
        }
    }

    public String printTask() {
        String check = super.isDone ? "[X] " : "[ ] ";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return this.by == null
                ? "[D]" + check + super.name + " (invalid date)"
                : "[D]" + check + super.name + " (" + this.by.format(formatter) + ")";
    }

    public String printToFile() {
        return super.isDone
                ? "D | 0 | " + super.name + " | " + this.by
                : "D | 1 | " + super.name + " | " + this.by;
    }
}