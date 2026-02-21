package demeter;// demeter.Ui.java
import java.util.Scanner;

public class Ui {

    private final Scanner scanner = new Scanner(System.in);
    private String lastMessage;

    /**
     * Displays the welcome message.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Demeter.");
        System.out.println("What can I do for you?");
    }

    public String getLastMessage() {
        return lastMessage;
    }

    /**
     * Displays the goodbye message on exit.
     */
    public void showBye() {
        lastMessage = "Bye. Hope to see you again soon!";
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
        lastMessage = "Here are the tasks in your list:";
        for (int i = 0; i < tasks.size(); i++) {
            lastMessage += "\n" + (i + 1) + ". " + tasks.get(i).printTask();
        }

    }

    /**
     * Displays the message confirming which task has been successfully added.
     *
     * @param task Task to be added.
     * @param size Total number of tasks in the list after addition.
     */
    public void showAdd(Task task, int size) {
        lastMessage = "Got it. I've added this task:\n"
                + "  " + task.printTask() + "\n"
                + "Now you have " + size + " tasks in the list.";
    }

    /**
     * Displays the message confirming which task has been successfully deleted.
     *
     * @param task Task to be deleted.
     * @param size Total number of tasks in the list after deletion.
     */
    public void showDelete(Task task, int size) {
        lastMessage = "I've removed this task:\n"
                + "  " + task.printTask() + "\n"
                + "Now you have " + size + " tasks in the list.";
    }

    /**
     * Display message confirming which task has been successfully marked as completed.
     *
     * @param task Task to be marked as completed.
     */
    public void showMark(Task task) {
        lastMessage = "Nice! I've marked this task as done:"
                + "\n  " + task.printTask();
    }

    /**
     * Display message confirming which task has been successfully marked as incomplete.
     *
     * @param task Task to be marked as incomplete.
     */
    public void showUnmark(Task task) {
        lastMessage = "Ok, I've marked this task as not done yet:\n"
                + "  " + task.printTask();
    }

    /**
     * Stores a message to be displayed to the user.
     * The message can later be retrieved via {@code getLastMessage()}
     * for rendering in the GUI.
     *
     * @param message The message to be shown to the user.
     */
    public void showMessage(String message) {
        lastMessage = message;
    }

    /**
     * Display error message.
     *
     * @param message Error message to be displayed.
     */
    public void showError(String message) {
        lastMessage = message;
    }

    /**
     * Display error message when file fails to load.
     */
    public void showLoadingError() {
        lastMessage = "Error loading file. Starting with empty task list.";
    }
}
