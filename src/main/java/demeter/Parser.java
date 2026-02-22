package demeter;

public class Parser {

    public boolean isExit(String input) {
        return input.equals("bye");
    }

    public void execute(String input, TaskList tasks, Ui ui) throws DemeterException {
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
            handleMark(args, tasks, ui);
            break;
        case "unmark":
            handleUnmark(args, tasks, ui);
            break;
        case "delete":
            handleDelete(args, tasks, ui);
            break;
        case "todo":
            handleTodo(args, tasks, ui);
            break;
        case "deadline":
            handleDeadline(args, tasks, ui);
            break;
        case "event":
            handleEvent(args, tasks, ui);
            break;
        case "find":
            handleFind(args, tasks, ui);
            break;
        default:
            throw new DemeterException("Sorry, I don't know what you mean.");
        }
    }

    private void handleMark(String args, TaskList tasks, Ui ui) throws DemeterException {
        int index = parseIndex(args);
        assert index >= 0 : "Index for mark should not be negative";
        Task task = tasks.mark(index);
        assert task != null : "Marked task should not be null";
        ui.showMark(task);
    }

    private void handleUnmark(String args, TaskList tasks, Ui ui) throws DemeterException {
        int index = parseIndex(args);
        assert index >= 0 : "Index for unmark should not be negative";
        Task task = tasks.unmark(index);
        assert task != null : "Unmarked task should not be null";
        ui.showUnmark(task);
    }

    private void handleDelete(String args, TaskList tasks, Ui ui) throws DemeterException {
        int index = parseIndex(args);
        assert index >= 0 : "Index for delete should not be negative";
        Task task = tasks.delete(index);
        assert task != null : "Deleted task should not be null";
        ui.showDelete(task, tasks.size());
    }

    private void handleTodo(String args, TaskList tasks, Ui ui) throws DemeterException {
        String desc = args.substring(5).trim();
        assert !desc.isEmpty() : "Todo description should not be empty";
        Task task = tasks.add(new Todo(desc, false));
        assert task != null : "Added todo task should not be null";
        ui.showAdd(task, tasks.size());
    }

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

    private int parseIndex(String arg) throws DemeterException {
        try {
            return Integer.parseInt(arg) - 1;
        } catch (Exception e) {
            throw new DemeterException("Oh dear, some fields are missing!");
        }
    }
}