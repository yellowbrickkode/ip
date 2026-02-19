// Ui.java
import java.util.Scanner;

public class Ui {

    private final Scanner scanner = new Scanner(System.in);

    public void showWelcome() {
        System.out.println("Hello! I'm Demeter.");
        System.out.println("What can I do for you?");
    }

    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showTasks(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).printTask());
        }
    }

    public void showAdd(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.printTask());
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public void showDelete(Task task, int size) {
        System.out.println("I've removed this task:");
        System.out.println("  " + task.printTask());
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public void showMark(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task.printTask());
    }

    public void showUnmark(Task task) {
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println("  " + task.printTask());
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showLoadingError() {
        System.out.println("Error loading file. Starting with empty task list.");
    }
}
