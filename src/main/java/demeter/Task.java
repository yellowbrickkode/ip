package demeter;

public class Task {
    protected String name;
    protected boolean done;
    public static int idx = 0;
    private int id;
    public Task(String name, boolean done) {
        this.name = name;
        this.done = done;
        this.id = idx ++;
    }
    public String printTask() {
        return "";
    }
    public int getId() {
        return this.id;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    public String printToFile() {
        return null;
    }
}