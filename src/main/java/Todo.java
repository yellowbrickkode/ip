public class Todo extends Task {
    public Todo(String name, boolean done) {
        super(name.trim(), done);
    }

    public String printTask() {
        String check = super.done ? "[X] " : "[ ] ";
        return "[T]" + check + super.name;
    }

    public String printToFile() {
        return super.done
                ? "T | 0 | " + super.name
                : "T | 1 | " + super.name;
    }
}
