package demeter;

import java.util.Stack;
import java.util.ArrayList;

/**
 * Parses user input commands and dispatches them to the appropriate handlers.
 * Responsible for interpreting raw string input and coordinating between
 * {@link TaskList}, {@link Ui}, and undo history.
 */
public class Parser {

    /**
     * Checks whether the given input corresponds to the exit command.
     *
     * @param input User input string.
     * @return true if the input is the exit command ("bye"), false otherwise.
     */
    public boolean isExit(String input) {
        assert input != null : "Input to isExit() should not be null";
        return input.equals("bye");
    }

    /**
     * Executes a user command.
     * Splits the input into command and arguments, and delegates handling
     * to the corresponding method.
     *
     * @param history Stack storing previous states for undo functionality.
     * @param input   Raw user input.
     * @param tasks   Current task list.
     * @param ui      User interface for displaying output.
     * @throws DemeterException If the command is invalid or improperly formatted.
     */
    public void execute(Stack<TaskList> history, String input, TaskList tasks, Ui ui) throws DemeterException {

        assert input != null : "Input to execute() should not be null";
        assert tasks != null : "TaskList should be initialised before parsing";
        assert ui != null : "Ui should be initialised before parsing";

        String[] parts = input.trim().split(" ", 2);
        String command = parts[0];
        String args = parts.length > 1 ? parts[1].trim() : "";

        switch (command) {
        case "list":
            ui.showTasks(tasks);
            break;
        case "mark":
            history.push(copyTaskList(tasks));
            handleMark(args, tasks, ui);
            break;
        case "unmark":
            history.push(copyTaskList(tasks));
            handleUnmark(args, tasks, ui);
            break;
        case "delete":
            history.push(copyTaskList(tasks));
            handleDelete(args, tasks, ui);
            break;
        case "todo":
            history.push(copyTaskList(tasks));
            handleTodo(args, tasks, ui);
            break;
        case "deadline":
            history.push(copyTaskList(tasks));
            handleDeadline(args, tasks, ui);
            break;
        case "event":
            history.push(copyTaskList(tasks));
            handleEvent(args, tasks, ui);
            break;
        case "find":
            handleFind(args, tasks, ui);
            break;
        case "undo":
            handleUndo(history, tasks, ui);
            break;
        default:
            throw new DemeterException("Sorry, I don't know what you mean.");
        }
    }

    /**
     * Handles the mark command.
     */
    private void handleMark(String args, TaskList tasks, Ui ui) throws DemeterException {
        int index = getIndex(args);
        assert index >= 0 : "Index for mark should not be negative";
        Task task = tasks.mark(index);
        assert task != null : "Marked task should not be null";
        ui.showMark(task);
    }

    /**
     * Handles the unmark command.
     */
    private void handleUnmark(String args, TaskList tasks, Ui ui) throws DemeterException {
        int index = getIndex(args);
        assert index >= 0 : "Index for unmark should not be negative";
        Task task = tasks.unmark(index);
        assert task != null : "Unmarked task should not be null";
        ui.showUnmark(task);
    }

    /**
     * Handles the delete command.
     */
    private void handleDelete(String args, TaskList tasks, Ui ui) throws DemeterException {
        int index = getIndex(args);
        assert index >= 0 : "Index for delete should not be negative";
        Task task = tasks.delete(index);
        assert task != null : "Deleted task should not be null";
        ui.showDelete(task, tasks.size());
    }

    /**
     * Handles the todo command.
     */
    private void handleTodo(String args, TaskList tasks, Ui ui) throws DemeterException {
        assert !args.isEmpty() : "Todo description should not be empty";
        Task task = tasks.add(new Todo(args, false));
        assert task != null : "Added todo task should not be null";
        ui.showAdd(task, tasks.size());
    }


    /**
     * Handles the deadline command.
     */
    private void handleDeadline(String args, TaskList tasks, Ui ui) throws DemeterException {
        if (!args.contains("/by")) {
            throw new DemeterException("Oh dear, some fields are missing!");
        }
        String[] parts = args.split("/by");
        assert parts.length == 2 : "Deadline should contain description and /by part";

        Task task = tasks.add(new Deadline(parts[0].trim(), false, parts[1].trim()));
        assert task != null : "Added deadline task should not be null";
        ui.showAdd(task, tasks.size());
    }

    /**
     * Handles the event command.
     */
    private void handleEvent(String args, TaskList tasks, Ui ui) throws DemeterException {
        if (!args.contains("/from") || !args.contains("/to")) {
            throw new DemeterException("Oh dear, some fields are missing!");
        }

        String desc = args.split("/from")[0].trim();
        String from = args.split("/from")[1].split("/to")[0].trim();
        String to = args.split("/to")[1].trim();

        assert !desc.isEmpty() : "Event description should not be empty";
        assert !from.isEmpty() : "Event start time should not be empty";
        assert !to.isEmpty() : "Event end time should not be empty";

        Task task = tasks.add(new Event(desc, false, from, to));
        assert task != null : "Added event task should not be null";
        ui.showAdd(task, tasks.size());
    }

    /**
     * Handles the find command by searching tasks that contain the given keyword.
     */
    private void handleFind(String args, TaskList tasks, Ui ui) {
        String keyword = args.toLowerCase();
        assert !keyword.isEmpty() : "Keyword should not be empty";
        StringBuilder result = new StringBuilder("Here are the matching tasks in your list:");
        int count = 1;
        for (int i = 0; i < tasks.size(); i++) {
            assert tasks.get(i) != null : "Task in list should not be null";
            if (tasks.get(i).getName().toLowerCase().contains(keyword)) {
                result.append("\n").append(count++).append(". ").append(tasks.get(i).printTask());
            }
        }
        if (count == 1) {
            result.append("\nNo matching tasks found.");
        }
        ui.showMessage(result.toString());
    }

    /**
     * Restores the previous state of the task list using the undo history.
     */
    private void handleUndo(Stack<TaskList> history, TaskList tasks, Ui ui) {
        if (history.isEmpty()) {
            ui.showMessage("Nothing to undo!");
        } else {
            TaskList previous = history.pop();

            tasks.getTasks().clear();
            tasks.getTasks().addAll(previous.getTasks());

            ui.showMessage("Restored to previous state!");
        }
    }

    /**
     * Converts a 1-based user index into a 0-based internal index.
     *
     * @param arg String representation of index.
     * @return Zero-based index.
     * @throws DemeterException If parsing fails.
     */
    private int getIndex(String arg) throws DemeterException {
        assert arg != null : "Input to getIndex() should not be null";
        try {
            int index = Integer.parseInt(arg) - 1;
            assert index >= 0 : "Parsed index should not be negative";
            return index;
        } catch (Exception e) {
            throw new DemeterException("Oh dear, some fields are missing!");
        }
    }

    /**
     * Creates a deep copy of the given TaskList for undo functionality.
     *
     * @param tasks TaskList to copy.
     * @return Copied TaskList.
     */
    private TaskList copyTaskList(TaskList tasks) {
        ArrayList<Task> copied = new ArrayList<>();
        for (Task t : tasks.getTasks()) {
            copied.add(t.copy());
        }
        return new TaskList(copied);
    }
}