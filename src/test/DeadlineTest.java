import demeter.Task;
import demeter.Deadline;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {

    @BeforeEach
    void resetIndex() {
        Task.idx = 0;
    }

    @Test
    void printTask_validDate_correctFormatting() {
        Deadline d = new Deadline("submit report", false, "2026-02-28");

        assertEquals(
                "[D][ ] submit report (28-02-2026)",
                d.printTask()
        );
    }

    @Test
    void printTask_done_validDate_correctFormatting() {
        Deadline d = new Deadline("submit report", true, "2026-02-28");

        assertEquals(
                "[D][X] submit report (28-02-2026)",
                d.printTask()
        );
    }

    @Test
    void printTask_invalidDate_showsInvalidMessage() {
        Deadline d = new Deadline("submit report", false, "invalid-date");

        assertEquals(
                "[D][ ] submit report (invalid date)",
                d.printTask()
        );
    }


    @Test
    void printToFile_done_correctFormat() {
        Deadline d = new Deadline("submit report", true, "2026-02-28");

        assertEquals(
                "D | 0 | submit report | 2026-02-28",
                d.printToFile()
        );
    }

    @Test
    void printToFile_notDone_correctFormat() {
        Deadline d = new Deadline("submit report", false, "2026-02-28");

        assertEquals(
                "D | 1 | submit report | 2026-02-28",
                d.printToFile()
        );
    }
}