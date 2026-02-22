package demeter;

public class Parser {

    public boolean isExit(String input) {
        assert input != null : "Input to isExit() should not be null";
        return input.equals("bye");
    }

    public void execute(String input, TaskList tasks, Ui ui) throws DemeterException {

        assert input != null : "Input to execute() should not be null";
        assert tasks != null : "TaskList should be initialised before parsing";
        assert ui != null : "Ui should be initialised before parsing";

        if (input.equals("list")) {
            ui.showTasks(tasks);

        } else if (input.startsWith("mark")) {
            int index = getIndex(input, "mark");
            assert index >= 0 : "Index for mark should not be negative";
            Task task = tasks.mark(index);
            assert task != null : "Marked task should not be null";
            ui.showMark(task);

        } else if (input.startsWith("unmark")) {
            int index = getIndex(input, "unmark");
            assert index >= 0 : "Index for unmark should not be negative";
            Task task = tasks.unmark(index);
            assert task != null : "Unmarked task should not be null";
            ui.showUnmark(task);

        } else if (input.startsWith("delete")) {
            int index = getIndex(input, "delete");
            assert index >= 0 : "Index for delete should not be negative";
            Task task = tasks.delete(index);
            assert task != null : "Deleted task should not be null";
            ui.showDelete(task, tasks.size());

        } else if (input.startsWith("todo")) {
            String desc = input.substring(5).trim();
            assert !desc.isEmpty() : "Todo description should not be empty";
            Task task = tasks.add(new Todo(desc, false));
            assert task != null : "Added todo task should not be null";
            ui.showAdd(task, tasks.size());

        } else if (input.startsWith("deadline")) {
            if (!input.contains("/by")) {
                throw new DemeterException("Oh dear, some fields are missing!");
            }
            String[] parts = input.substring(9).split("/by");
            assert parts.length == 2 : "Deadline should contain description and /by part";

            Task task = tasks.add(new Deadline(parts[0].trim(), false, parts[1].trim()));
            assert task != null : "Added deadline task should not be null";
            ui.showAdd(task, tasks.size());

        } else if (input.startsWith("event")) {
            if (!input.contains("/from") || !input.contains("/to")) {
                throw new DemeterException("Oh dear, some fields are missing!");
            }

            String desc = input.substring(6).split("/from")[0].trim();
            String from = input.split("/from")[1].split("/to")[0].trim();
            String to = input.split("/to")[1].trim();

            assert !desc.isEmpty() : "Event description should not be empty";
            assert !from.isEmpty() : "Event start time should not be empty";
            assert !to.isEmpty() : "Event end time should not be empty";

            Task task = tasks.add(new Event(desc, false, from, to));
            assert task != null : "Added event task should not be null";
            ui.showAdd(task, tasks.size());

        } else if (input.startsWith("find")) {
            String keyword = input.substring(5).trim().toLowerCase();
            assert !keyword.isEmpty() : "Keyword should not be empty";

            StringBuilder result = new StringBuilder();
            result.append("Here are the matching tasks in your list:");

            int count = 1;
            for (int i = 0; i < tasks.size(); i++) {
                assert tasks.get(i) != null : "Task in list should not be null";
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
        assert input != null : "Input to getIndex() should not be null";

        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            assert index >= 0 : "Parsed index should not be negative";
            return index;
        } catch (Exception e) {
            throw new DemeterException("Oh dear, some fields are missing!");
        }
    }
}