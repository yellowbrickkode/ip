package demeter;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages a collection of Task objects.
 */
public class TaskList {

    /** Internal list storing tasks. */
    private List<Task> tasks;

    /** Constructs an empty TaskList. */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with an existing list.
     *
     * @param tasks Preloaded list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to add.
     * @return The added task.
     */
    public Task add(Task task) {
        assert task != null : "Cannot add null task";
        tasks.add(task);
        assert tasks.contains(task) : "Task should be present after adding";
        return task;
    }

    /**
     * Deletes a task from the list.
     * @param index Index of task to delete.
     * @return The deleted task.
     */
    public Task delete(int index) {
        assert isValidIndex(index) : "Delete index out of bounds";
        Task deleted = tasks.remove(index);
        assert deleted != null : "Removed task should not be null";
        return deleted;
    }

    /**
     * Marks a task as completed.
     * @param index Index of task to mark as completed.
     * @return The task to mark as completed.
     */
    public Task mark(int index) {
        assert isValidIndex(index) : "Mark index out of bounds";
        Task task = tasks.get(index);
        assert task != null : "Task to mark should not be null";
        task.mark();
        return task;
    }

    /**
     * Marks a task as incomplete.
     * @param index Index of task to mark as incomplete.
     * @return The task to mark as incomplete.
     */
    public Task unmark(int index) {
        assert isValidIndex(index) : "Unmark index out of bounds";
        Task task = tasks.get(index);
        assert task != null : "Task to unmark should not be null";
        task.unmark();
        return task;
    }

    /**
     * Retrieves task at a given index.
     * @param index Index of task to retrieve.
     * @return The task at the given index.
     */
    public Task get(int index) {
        assert isValidIndex(index) : "Get index out of bounds";
        Task task = tasks.get(index);
        assert task != null : "Retrieved task should not be null";
        return task;
    }

    /** @return number of tasks in the task list */
    public int size() {
        assert tasks != null : "Task list should never be null";
        return tasks.size();
    }

    /** @return underlying task list */
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