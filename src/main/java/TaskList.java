// TaskList.java
import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Task add(Task task) {
        tasks.add(task);
        return task;
    }

    public Task delete(int index) {
        return tasks.remove(index);
    }

    public Task mark(int index) {
        Task task = tasks.get(index);
        task.mark();
        return task;
    }

    public Task unmark(int index) {
        Task task = tasks.get(index);
        task.unmark();
        return task;
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
