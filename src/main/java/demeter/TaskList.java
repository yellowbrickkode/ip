package demeter;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Task add(Task task) {
        assert task != null : "Cannot add null task";
        tasks.add(task);
        assert tasks.contains(task) : "Task should be present after adding";
        return task;
    }

    public Task delete(int index) {
        assert isValidIndex(index) : "Delete index out of bounds";
        Task removed = tasks.remove(index);
        assert removed != null : "Removed task should not be null";
        return removed;
    }

    public Task mark(int index) {
        assert isValidIndex(index) : "Mark index out of bounds";
        Task task = tasks.get(index);
        assert task != null : "Task to mark should not be null";
        task.mark();
        return task;
    }

    public Task unmark(int index) {
        assert isValidIndex(index) : "Unmark index out of bounds";
        Task task = tasks.get(index);
        assert task != null : "Task to unmark should not be null";
        task.unmark();
        return task;
    }

    public Task get(int index) {
        assert isValidIndex(index) : "Get index out of bounds";
        Task task = tasks.get(index);
        assert task != null : "Retrieved task should not be null";
        return task;
    }

    public int size() {
        assert tasks != null : "Task list should never be null";
        return tasks.size();
    }

    public List<Task> getTasks() {
        assert tasks != null : "Task list should never be null";
        return tasks;
    }

    /**
     * Checks whether the index is within the valid range.
     */
    private boolean isValidIndex(int index) {
        return index >= 0 && index < tasks.size();
    }
}