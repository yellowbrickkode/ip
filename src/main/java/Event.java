public class Event extends Task {
    private String timeInfo;
    public Event(String name, boolean done, String timeInfo) {
        super(name, done);
        this.timeInfo = timeInfo;
    }

    public String printTask() {
        String check = super.done ? "[X] " : "[ ] ";
        return "[E]" + check + super.name + this.timeInfo;
    }
}