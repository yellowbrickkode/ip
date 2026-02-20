package demeter;// demeter.Ui.java
import java.util.Scanner;

public class Ui {

    private final Scanner scanner = new Scanner(System.in);

    /**
     * Displays the welcome message.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Demeter.");
        System.out.println("What can I do for you?");
    }

    /**
     * Displays the goodbye message on exit.
     */
    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Returns the input given.
     *
     * @return user input.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasks List of tasks to be displayed.
     */
    public void showTasks(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).printTask());
        }
    }

    /**
     * Displays the message confirming which task has been successfully added.
     *
     * @param task Task to be added.
     * @param size Total number of tasks in the list after addition.
     */
    public void showAdd(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.printTask());
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Displays the message confirming which task has been successfully deleted.
     *
     * @param task Task to be deleted.
     * @param size Total number of tasks in the list after deletion.
     */
    public void showDelete(Task task, int size) {
        System.out.println("I've removed this task:");
        System.out.println("  " + task.printTask());
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Display message confirming which task has been successfully marked as completed.
     *
     * @param task Task to be marked as completed.
     */
    public void showMark(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task.printTask());
    }

    /**
     * Display message confirming which task has been successfully marked as incomplete.
     *
     * @param task Task to be marked as incomplete.
     */
    public void showUnmark(Task task) {
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println("  " + task.printTask());
    }

    /**
     * Display error message.
     *
     * @param message Error message to be displayed.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Display error message when file fails to load.
     */
    public void showLoadingError() {
        System.out.println("Error loading file. Starting with empty task list.");
    }
}
