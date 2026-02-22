package demeter;

public class Todo extends Task {

    public Todo(String name, boolean isDone) {
        super(name.trim(), isDone);
    }

    public String printTask() {
        return super.isDone
                ? "[T][X] " + super.name
                : "[T][ ] " + super.name;
    }

    public String printToFile() {
        return super.isDone
                ? "T | 1 | " + super.name
                : "T | 0 | " + super.name;
    }

    @Override
    public Task copy() {
        return new Todo(this.getName(), this.isDone());
    }
}
