import java.util.Scanner;

public class Demeter {
    public static void main(String[] args) throws DemeterExceptions {
        Scanner sc = new Scanner(System.in);
        Task[] lst = new Task[100];
        String error = "";

        System.out.println("Hello! I'm Demeter.\nWhat can I do for you? \n");

        while (true) {
            String input = sc.nextLine();
            boolean addSuccess = false;
            if (input.equals("bye")) {
                break;

            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < Task.idx; i++) {
                    int line = i + 1;
                    System.out.println(line + ". " + lst[i].printTask());
                }

            } else if (input.startsWith("mark")) {
                int taskNum = Integer.parseInt(input.split(" ")[1]) - 1;
                for (int i = 0; i < Task.idx; i++) {
                    if (lst[i].getId() == taskNum) {
                        lst[i].mark();
                        System.out.println("Nice! I've marked this task as done: \n  " + lst[i].printTask());
                    }
                }

            } else if (input.startsWith("unmark")) {
                int taskNum = Integer.parseInt(input.split(" ")[1]) - 1;
                for (int i = 0; i < Task.idx; i++) {
                    if (lst[i].getId() == taskNum) {
                        lst[i].unmark();
                        System.out.println("Ok, I've marked this task as not done yet: \n  " + lst[i].printTask());
                    }
                }

            } else if (input.startsWith("todo")) {
                if (input.strip().equals("todo")) {
                    error = "A todo requires a description.";
                } else {
                    lst[Task.idx] = new Todo(input.substring(5), false);
                    addSuccess = true;
                    printAdd(lst[Task.idx - 1].printTask());
                }

            } else if (input.startsWith("deadline")) {
                String trimmed = input.strip();

                // Remove "deadline"
                String remainder = trimmed.length() > 8 ? trimmed.substring(8).trim() : "";

                // Case: "deadline"
                if (remainder.isEmpty()) {
                    error = "A deadline requires a description and a due date.";

                    // Case: "deadline /by" or "deadline /by Sunday"
                } else if (remainder.startsWith("/by")) {
                    String dueDate = remainder.length() > 3 ? remainder.substring(3).trim() : "";

                    if (dueDate.isEmpty()) {
                        error = "A deadline requires a description and a due date.";
                    } else {
                        error = "A deadline requires a description.";
                    }

                    // Normal cases
                } else if (!remainder.contains("/by")) {
                    error = "A deadline requires a due date.";

                } else {
                    String[] parts = remainder.split(" /by ", 2);
                    String description = parts[0].trim();
                    String dueDate = parts.length > 1 ? parts[1].trim() : "";

                    if (description.isEmpty() && dueDate.isEmpty()) {
                        error = "A deadline requires a description and a due date.";
                    } else if (description.isEmpty()) {
                        error = "A deadline requires a description.";
                    } else if (dueDate.isEmpty()) {
                        error = "A deadline requires a due date.";
                    } else {
                        lst[Task.idx] = new Deadline(
                                description,
                                false,
                                " (by: " + dueDate + ")"
                        );
                        addSuccess = true;
                        printAdd(lst[Task.idx - 1].printTask());
                    }
                }
            } else if (input.startsWith("event")) {
                String trimmed = input.strip();
                String remainder = trimmed.length() > 5 ? trimmed.substring(5).trim() : "";

                String description = "";
                String start = "";
                String end = "";

                // Extract description (only if it appears before any keyword)
                if (!remainder.startsWith("/from") && !remainder.startsWith("/to")) {
                    description = remainder.split(" /from | /to ", 2)[0].trim();
                }

                // Extract start time (empty if missing or "/from" has no value)
                if (remainder.contains("/from")) {
                    String[] fromSplit = remainder.split(" /from ", 2);
                    if (fromSplit.length > 1) {
                        start = fromSplit[1].split(" /to ", 2)[0].trim();
                    }
                }

                // Extract end time (empty if missing or "/to" has no value)
                if (remainder.contains("/to")) {
                    String[] toSplit = remainder.split(" /to ", 2);
                    if (toSplit.length > 1) {
                        end = toSplit[1].trim();
                    }
                }

                // Validation flags
                boolean missingDescription = description.isEmpty();
                boolean missingStart = start.isEmpty();
                boolean missingEnd = end.isEmpty();

                // Error handling (deterministic & complete)
                if (missingDescription && missingStart && missingEnd) {
                    error = "An event requires a description, a start time and an end time.";

                } else if (missingDescription && missingStart) {
                    error = "An event requires a description and a start time.";

                } else if (missingDescription && missingEnd) {
                    error = "An event requires a description and an end time.";

                } else if (missingStart && missingEnd) {
                    error = "An event requires a start time and an end time.";

                } else if (missingDescription) {
                    error = "An event requires a description.";

                } else if (missingStart) {
                    error = "An event requires a start time.";

                } else if (missingEnd) {
                    error = "An event requires an end time.";

                } else {
                    lst[Task.idx] = new Event(
                            description,
                            false,
                            " (from: " + start + " to: " + end + ")"
                    );
                    addSuccess = true;
                    printAdd(lst[Task.idx - 1].printTask());
                }
            }
            if (!addSuccess) {
                try {
                    validate(error);
                } catch (DemeterExceptions e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }

    private static void validate(String errorMessage) throws DemeterExceptions {
        if (!errorMessage.isEmpty()) {
            throw new DemeterExceptions(errorMessage);
        }
    }

    private static void printAdd(String task) {
        System.out.println("Got it. I've added this task:\n  " + task);
        System.out.println("Now you have " + (Task.idx) + " tasks in the list.");
    }
}
