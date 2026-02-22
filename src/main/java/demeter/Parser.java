package demeter;

public class Parser {

    public boolean isExit(String input) {
        return "bye".equals(input);
    }

    public void execute(String input, TaskList tasks, Ui ui) throws DemeterException {
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
        Task task = tasks.mark(index);
        ui.showMark(task);
    }

    private void handleUnmark(String args, TaskList tasks, Ui ui) throws DemeterException {
        int index = parseIndex(args);
        Task task = tasks.unmark(index);
        ui.showUnmark(task);
    }

    private void handleDelete(String args, TaskList tasks, Ui ui) throws DemeterException {
        int index = parseIndex(args);
        Task task = tasks.delete(index);
        ui.showDelete(task, tasks.size());
    }

    private void handleTodo(String args, TaskList tasks, Ui ui) throws DemeterException {
        if (args.isEmpty()) throw new DemeterException("The description of a todo cannot be empty!");
        Task task = tasks.add(new Todo(args, false));
        ui.showAdd(task, tasks.size());
    }

    private void handleDeadline(String args, TaskList tasks, Ui ui) throws DemeterException {
        if (!args.contains("/by")) {
            throw new DemeterException("Oh dear, some fields are missing!");
        }

        String[] parts = args.split("/by");
        Task task = tasks.add(new Deadline(parts[0].trim(), false, parts[1].trim()));
        ui.showAdd(task, tasks.size());
    }

    private void handleEvent(String args, TaskList tasks, Ui ui) throws DemeterException {
        if (!args.contains("/from") || !args.contains("/to")) {
            throw new DemeterException("Oh dear, some fields are missing!");
        }

        String description = args.split("/from")[0].trim();
        String from = args.split("/from")[1].split("/to")[0].trim();
        String to = args.split("/to")[1].trim();
        Task task = tasks.add(new Event(description, false, from, to));
        ui.showAdd(task, tasks.size());
    }

    private void handleFind(String args, TaskList tasks, Ui ui) {
        String keyword = args.toLowerCase();
        StringBuilder result = new StringBuilder("Here are the matching tasks in your list:");

        int count = 1;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getName().toLowerCase().contains(keyword)) {
                result.append("\n").append(count++).append(". ").append(task.printTask());
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