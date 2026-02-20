package demeter;// demeter.Demeter.java
import java.io.IOException;

public class Demeter {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructs a new Demeter application instance.
     * Initializes the user interface, storage, parser, and loads existing tasks from the given file path.
     * If loading fails, initializes with an empty task list.
     *
     * @param filePath the path to the file used for storing tasks
     */
    public Demeter(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();

        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main program loop of the Demeter application.
     * Repeatedly reads user commands, executes them, updates storage, and handles errors.
     * The loop continues until the user issues the exit command.
     */
    public void run() {
        ui.showWelcome();
        while (true) {
            String input = ui.readCommand();

            if (parser.isExit(input)) {
                break;
            }

            try {
                parser.execute(input, tasks, ui);
                storage.save(tasks.getTasks());
            } catch (DemeterException | IOException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.showBye();
    }

    /**
     * Entry point of the Demeter application.
     * Creates a new Demeter instance with the default storage file path and runs it.
     * @param args command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Demeter("data/demeter.txt").run();
    }
}
