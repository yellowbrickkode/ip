import java.util.Scanner;

public class Demeter {
    public static void main(String[] args) throws DemeterExceptions {
        Scanner sc = new Scanner(System.in);
        Task[] lst = new Task[100];

        System.out.println("Hello! I'm Demeter.\nWhat can I do for you? \n");

        while (true) {
            String input = sc.nextLine();
            String error = "";
            if (input.equals("bye")) {
                break;

            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < Task.idx; i++) {
                    int line = i + 1;
                    System.out.println(line + ". " + lst[i].printTask());
                }

            } else if (input.startsWith("mark")) {
                if (input.strip().equals("mark")) {
                    error = "Oh dear, some fields are missing!";
                } else {
                    int taskNum = Integer.parseInt(input.split(" ")[1]) - 1;
                    for (int i = 0; i < Task.idx; i++) {
                        if (lst[i].getId() == taskNum) {
                            lst[i].mark();
                            System.out.println("Nice! I've marked this task as done: \n  " + lst[i].printTask());
                        }
                    }
                }

            } else if (input.startsWith("unmark")) {
                if (input.strip().equals("unmark")) {
                    error = "Oh dear, some fields are missing!";
                } else {
                    int taskNum = Integer.parseInt(input.split(" ")[1]) - 1;
                    for (int i = 0; i < Task.idx; i++) {
                        if (lst[i].getId() == taskNum) {
                            lst[i].unmark();
                            System.out.println("Ok, I've marked this task as not done yet: \n  " + lst[i].printTask());
                        }
                    }
                }
            } else if (input.startsWith("delete")) {
                if (input.strip().equals("delete")) {
                    error = "Oh dear, some fields are missing!";
                } else {
                    int taskNum = Integer.parseInt(input.split(" ")[1]) - 1;
                    String deletedTask = lst[taskNum].printTask();
                    Task.idx --;
                    for (int i = taskNum; i < Task.idx - 1; i++) {
                        lst[i] = lst[i + 1];
                    }
                    System.out.println("I've removed this task: \n" + deletedTask + "\nYou now have " + Task.idx + " tasks in the list.");
                }
            } else if (input.startsWith("todo")) {
                if (input.strip().equals("todo")) {
                    error = "Oh dear, some fields are missing!";
                } else {
                    lst[Task.idx] = new Todo(input.substring(5), false);
                    printAdd(lst[Task.idx - 1].printTask());
                }

            } else if (input.startsWith("deadline")) {
                if (!input.strip().contains("/by")) {
                    error = "Oh dear, some fields are missing!";
                } else {
                    lst[Task.idx] = new Deadline(input.substring(9).split("/by")[0], false, input.split("/by")[1]);
                    printAdd(lst[Task.idx-1].printTask());
                }
            } else if (input.startsWith("event")) {
                if (!input.strip().contains("/from") || !input.strip().contains("/to")) {
                    error = "Oh dear, some fields are missing!";
                } else {
                    lst[Task.idx] = new Event(input.substring(6).split("/from")[0], false, input.strip().split("/from")[1].split("/to")[0], input.strip().split("/to")[1]);
                    printAdd(lst[Task.idx - 1].printTask());
                }
            } else {
                error = "Sorry, I don't know what you mean.";
            }
            if (!error.isBlank()) {
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
        System.out.println("Now you have " + Task.idx + " tasks in the list.");
    }
}
