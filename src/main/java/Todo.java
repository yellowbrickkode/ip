public class Todo extends Task {
    public Todo(String name, boolean done) {
        super(name, done);
    }

    public String printTask() {
        String check = super.done ? "[X] " : "[ ] ";
        return "[T]" + check + super.name;
    }
}
