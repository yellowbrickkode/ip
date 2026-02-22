package demeter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Handles loading and saving tasks to persistent storage.
 * Tasks are stored in a text file in a structured format.
 */
public class Storage {

    /** Path to the storage file. */
    private final Path filePath;

    /**
     * Constructs a Storage instance with default file location
     * at data/demeter.txt.
     */
    public Storage() {
        this.filePath = Paths.get("data", "demeter.txt");
    }

    /**
     * Loads tasks from the storage file.
     *
     * @return List of tasks loaded from file.
     * @throws IOException If file reading fails.
     */
    public List<Task> load() throws IOException {
        List<Task> tasks = new ArrayList<>();

        // If file does not exist, return empty list
        if (!Files.exists(filePath)) {
            return tasks;
        }

        Files.createDirectories(filePath.getParent());
        Scanner scanner = new Scanner(filePath);

        // Read file line by line
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            assert line != null : "Line read from file should not be null";
            assert !line.isBlank() : "Stored task line should not be blank";

            String[] parts = line.split(" \\| ");
            assert parts.length >= 3 : "Stored task format is invalid";

            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];

            assert description != null : "Task description should not be null";

            switch (type) {
            case "T":
                tasks.add(new Todo(description, isDone));
                break;

            case "D":
                assert parts.length == 4 : "Deadline should have 4 fields";
                tasks.add(new Deadline(description, isDone, parts[3]));
                break;

            case "E":
                assert parts.length == 4 : "Event should have 4 fields";
                String[] times = parts[3].split(" - ");
                assert times.length == 2 : "Event time format invalid";
                tasks.add(new Event(description, isDone, times[0], times[1]));
                break;

            default:
                assert false : "Unknown task type in storage file";
            }
        }

        scanner.close();
        return tasks;
    }

    /**
     * Saves the given list of tasks to the storage file.
     *
     * @param tasks List of tasks to save.
     * @throws IOException If writing fails.
     */
    public void save(List<Task> tasks) throws IOException {
        assert tasks != null : "Task list to save should not be null";

        Files.createDirectories(filePath.getParent());
        BufferedWriter writer = Files.newBufferedWriter(filePath);

        // Write each task in file format
        for (Task task : tasks) {
            assert task != null : "Task in list should not be null";
            writer.write(task.printToFile());
            writer.newLine();
        }

        writer.close();
    }
}