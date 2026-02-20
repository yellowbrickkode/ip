package demeter;

public class Task {

    protected String name;
    protected boolean isDone;
    public static int idx = 0;
    private int id;

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
        this.id = idx ++;
    }

    public String printTask() {
        return "";
    }

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

    public boolean isDone() {
        return this.isDone;
    }
}