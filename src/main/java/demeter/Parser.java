package demeter;

// demeter.Parser.java
public class Parser {

    public boolean isExit(String input) {
        return input.equals("bye");
    }

    public void execute(String input, TaskList tasks, Ui ui) throws DemeterExceptions {
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
                throw new DemeterExceptions("Oh dear, some fields are missing!");
            }
            String[] parts = input.substring(9).split("/by");
            Task task = tasks.add(new Deadline(parts[0].trim(), false, parts[1].trim()));
            ui.showAdd(task, tasks.size());

        } else if (input.startsWith("event")) {
            if (!input.contains("/from") || !input.contains("/to")) {
                throw new DemeterExceptions("Oh dear, some fields are missing!");
            }
            String desc = input.substring(6).split("/from")[0].trim();
            String from = input.split("/from")[1].split("/to")[0].trim();
            String to = input.split("/to")[1].trim();
            Task task = tasks.add(new Event(desc, false, from, to));
            ui.showAdd(task, tasks.size());

        } else {
            throw new DemeterExceptions("Sorry, I don't know what you mean.");
        }
    }

    private int getIndex(String input, String command) throws DemeterExceptions {
        try {
            return Integer.parseInt(input.split(" ")[1]) - 1;
        } catch (Exception e) {
            throw new DemeterExceptions("Oh dear, some fields are missing!");
        }
    }
}
