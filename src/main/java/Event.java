public class Event extends Task {
    private String start;
    private String end;
    public Event(String name, boolean done, String start, String end) {
        super(name, done);
        this.start = start;
        this.end = end;
    }

    public String printTask() {
        String check = super.done ? "[X] " : "[ ] ";
        return "[E]" + check + super.name + this.start + this.end;
    }
}