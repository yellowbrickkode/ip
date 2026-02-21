package demeter;

// demeter.Parser.java
public class Parser {

    /**
     * Checks whether the given input is the exit command.
     * @param input User input string.
     * @return true if input is "bye", otherwise false.
     */
    public boolean isExit(String input) {
        return input.equals("bye");
    }

    /**
     * Handles all user inputs.
     * Supports commands: list, mark, unmark, delete, todo, deadline, event, find.
     * @param input User input string.
     * @param tasks List of all current tasks.
     * @param ui the Ui instance for displaying messages to the user.
     * @throws DemeterException if the input command is invalid or required fields are missing.
     */
    public void execute(String input, TaskList tasks, Ui ui) throws DemeterException {
        if (input.equals("list")) {
            ui.showTasks(tasks);

        } else if (input.startsWith("mark")) {
            int index = getIndex(input, "mark");
            Task task = tasks.mark(index);
            ui.showMark(task);

        } else if (input.startsWith("unmark")) {
            int index = getIndex(input, "unmark");
            Task task = tasks.unmark(index);
            ui.showUnmark(task);

        } else if (input.startsWith("delete")) {
            int index = getIndex(input, "delete");
            Task task = tasks.delete(index);
            ui.showDelete(task, tasks.size());

        } else if (input.startsWith("todo")) {
            String desc = input.substring(5).trim();
            Task task = tasks.add(new Todo(desc, false));
            ui.showAdd(task, tasks.size());

        } else if (input.startsWith("deadline")) {
            if (!input.contains("/by")) {
                throw new DemeterException("Oh dear, some fields are missing!");
            }
            String[] parts = input.substring(9).split("/by");
            Task task = tasks.add(new Deadline(parts[0].trim(), false, parts[1].trim()));
            ui.showAdd(task, tasks.size());

        } else if (input.startsWith("event")) {
            if (!input.contains("/from") || !input.contains("/to")) {
                throw new DemeterException("Oh dear, some fields are missing!");
            }
            String desc = input.substring(6).split("/from")[0].trim();
            String from = input.split("/from")[1].split("/to")[0].trim();
            String to = input.split("/to")[1].trim();
            Task task = tasks.add(new Event(desc, false, from, to));
            ui.showAdd(task, tasks.size());
        } else if (input.startsWith("find")) {
            String keyword = input.substring(5).trim().toLowerCase();
            StringBuilder result = new StringBuilder();

            result.append("Here are the matching tasks in your list:");

            int count = 1;
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i).getName().toLowerCase().contains(keyword)) {
                    result.append("\n")
                            .append(count)
                            .append(". ")
                            .append(tasks.get(i).printTask());
                    count++;
                }
            }
            if (count == 1) {
                result.append("\nNo matching tasks found.");
            }

            ui.showMessage(result.toString());
        } else {
            throw new DemeterException("Sorry, I don't know what you mean.");
        }
    }

    private int getIndex(String input, String command) throws DemeterException {
        try {
            return Integer.parseInt(input.split(" ")[1]) - 1;
        } catch (Exception e) {
            throw new DemeterException("Oh dear, some fields are missing!");
        }
    }
}
