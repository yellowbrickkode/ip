public class Deadline extends Task {
    private String timeInfo;
    public Deadline(String name, boolean done, String timeInfo) {
        super(name, done);
        this.timeInfo = timeInfo;
    }

    public String printTask() {
        String check = super.done ? "[X] " : "[ ] ";
        return "[D]" + check + super.name + this.timeInfo;
    }
}