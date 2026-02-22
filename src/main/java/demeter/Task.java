package demeter;

public class Task {
    protected String name;
    protected boolean isDone;
    public static int idx = 0;
    private int id;
    public Task(String name, boolean done) {
        assert !name.isEmpty() : "Task name should not be empty";
        this.name = name;
        this.isDone = done;
        this.id = idx ++;
    }
    public String printTask() {
        return "";
    }

    public String getName() { return this.name; }
    public int getId() {
        return this.id;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String printToFile() {
        return null;
    }

    public boolean isDone() { return this.isDone; }
}