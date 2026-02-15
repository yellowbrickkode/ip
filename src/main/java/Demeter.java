import java.io.BufferedWriter;
import java.io.*;
import java.nio.file.*;
import java.util.Scanner;

public class Demeter {

    static Path file = Paths.get("data", "duke.txt");
    private final static String files = "./data/todolist.txt";

    public static void main(String[] args) throws DemeterExceptions {
        Scanner sc = new Scanner(System.in);
        Task[] tasklist = loadFromFile();

        System.out.println("Hello! I'm Demeter.\nWhat can I do for you? \n");

        while (true) {
            String input = sc.nextLine();
            String error = "";

            // exit
            if (input.equals("bye")) {
                break;

                // list all tasks
            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < Task.idx; i++) {
                    int line = i + 1;
                    System.out.println(line + ". " + tasklist[i].printTask());
                }

                // mark a task
            } else if (input.startsWith("mark")) {
                if (input.strip().equals("mark")) {
                    error = "Oh dear, some fields are missing!";
                } else {
                    int taskNum = Integer.parseInt(input.split(" ")[1]) - 1;
                    for (int i = 0; i < Task.idx; i++) {
                        if (tasklist[i].getId() == taskNum) {
                            tasklist[i].mark();
                            System.out.println("Nice! I've marked this task as done: \n  "
                                    + tasklist[i].printTask());
                        }
                    }
                    saveToFile(tasklist);
                }

                // unmark a task
            } else if (input.startsWith("unmark")) {
                if (input.strip().equals("unmark")) {
                    error = "Oh dear, some fields are missing!";
                } else {
                    int taskNum = Integer.parseInt(input.split(" ")[1]) - 1;
                    for (int i = 0; i < Task.idx; i++) {
                        if (tasklist[i].getId() == taskNum) {
                            tasklist[i].unmark();
                            System.out.println("Ok, I've marked this task as not done yet: \n  "
                                    + tasklist[i].printTask());
                        }
                    }
                    saveToFile(tasklist);
                }

                // deletes task
            } else if (input.startsWith("delete")) {
                if (input.strip().equals("delete")) {
                    error = "Oh dear, some fields are missing!";
                } else {
                    int taskNum = Integer.parseInt(input.split(" ")[1]) - 1;
                    String deletedTask = tasklist[taskNum].printTask();
                    Task.idx --;
                    for (int i = taskNum; i < Task.idx - 1; i++) {
                        tasklist[i] = tasklist[i + 1];
                    }
                    System.out.println("I've removed this task: \n  "
                            + deletedTask
                            + "\nYou now have " + Task.idx + " tasks in the list.");
                    saveToFile(tasklist);
                }


                // add a to do task
            } else if (input.startsWith("todo")) {
                if (input.strip().equals("todo")) {
                    error = "Oh dear, some fields are missing!";
                } else {
                    tasklist[Task.idx] = new Todo(input.substring(5), false);
                    printAdd(tasklist[Task.idx - 1].printTask());
                    saveToFile(tasklist);
                }

                // add a deadline task
            } else if (input.startsWith("deadline")) {
                if (!input.strip().contains("/by")) {
                    error = "Oh dear, some fields are missing!";
                } else {
                    tasklist[Task.idx] = new Deadline(input.substring(9).split("/by")[0], false, input.split("/by")[1]);
                    printAdd(tasklist[Task.idx-1].printTask());
                    saveToFile(tasklist);
                }

                // add an event
            } else if (input.startsWith("event")) {
                if (!input.strip().contains("/from") || !input.strip().contains("/to")) {
                    error = "Oh dear, some fields are missing!";
                } else {
                    tasklist[Task.idx] = new Event(input.substring(6).split("/from")[0], false, input.strip().split("/from")[1].split("/to")[0], input.strip().split("/to")[1]);
                    printAdd(tasklist[Task.idx - 1].printTask());
                    saveToFile(tasklist);
                }

                // demeter does not recognize command
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

    // validates inputs
    private static void validate(String errorMessage) throws DemeterExceptions {
        if (!errorMessage.isEmpty()) {
            throw new DemeterExceptions(errorMessage);
        }
    }

    // prints String to console whenever new task is added
    private static void printAdd(String task) {
        System.out.println("Got it. I've added this task:\n  " + task);
        System.out.println("Now you have " + Task.idx + " tasks in the list.");
    }

    // loads list from hard disk
    private static Task[] loadFromFile() {
        Task[] taskList = new Task[100];
        if (!Files.exists(file)) {
            return taskList; // return empty list when file does not exist
        }
        try {
            Files.createDirectories(file.getParent());
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(" \\| ");

                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];

                switch (type) {
                    case "T":
                        taskList[Task.idx] = new Todo(description, isDone);
                        break;

                    case "D":
                        String by = parts[3];
                        taskList[Task.idx] = new Deadline(description, isDone, by);
                        break;

                    case "E":
                        String[] times = parts[3].split(" - ");
                        taskList[Task.idx] = new Event(description, isDone, times[0], times[1]);
                        break;
                }
            }
            fileScanner.close();
        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
        return taskList;
    }

    // saves list to hard disk
    private static void saveToFile(Task[] taskList) {
        try {
            Files.createDirectories(file.getParent());
            BufferedWriter writer = Files.newBufferedWriter(file);

            for (int i = 0; i < Task.idx; i++) {
                writer.write(taskList[i].printToFile());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}
