package demeter;// demeter.TaskList.java
import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private final List<Task> tasks;

    /**
     * Constructor for TaskList; creates an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor for TaskList; creates a TaskList given a list of tasks.
     * @param tasks List of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the TaskList.
     * @param task Task to be added.
     * @return Task that was added.
     */
    public Task add(Task task) {
        tasks.add(task);
        return task;
    }

    /**
     * Deletes a task from the TaskList.
     * @param index Index of task to be deleted.
     * @return Task that was deleted.
     */
    public Task delete(int index) {
        return tasks.remove(index);
    }

    /**
     * Marks a task from the TaskList as completed.
     * @param index Index of task to be marked as completed.
     * @return Task that was marked as completed.
     */
    public Task mark(int index) {
        Task task = tasks.get(index);
        task.mark();
        return task;
    }

    /**
     * Marks a task from the TaskList as incomplete.
     * @param index Index of task to be marked as incomplete.
     * @return Task that was marked as incomplete.
     */
    public Task unmark(int index) {
        Task task = tasks.get(index);
        task.unmark();
        return task;
    }

    /**
     * Gets a task at the specified index.
     * @param index Index of task to be retrieved.
     * @return Task that was retrieved.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks currently in the TaskList.
     * @return The size of the TaskList.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns all the tasks in the TaskList.
     * @return List of all tasks in the TaskList.
     */
    public List<Task> getTasks() {
        return tasks;
    }
}
