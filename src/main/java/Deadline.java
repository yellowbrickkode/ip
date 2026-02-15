public class Deadline extends Task {
    private String timeInfo;
    public Deadline(String name, boolean done, String timeInfo) {
        super(name.trim(), done);
        this.timeInfo = timeInfo;
    }

    public String printTask() {
        String check = super.done ? "[X] " : "[ ] ";
        return "[D]" + check + super.name + this.timeInfo;
    }

    public String printToFile() {
        return super.done
                ? "D | 0 | " + super.name + " | " + this.timeInfo
                : "D | 1 | " + super.name + " | " + this.timeInfo;
    }
}