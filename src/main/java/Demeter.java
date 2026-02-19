// Demeter.java
import java.io.IOException;

public class Demeter {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

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
            } catch (DemeterExceptions | IOException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.showBye();
    }

    public static void main(String[] args) {
        new Demeter("data/duke.txt").run();
    }
}
