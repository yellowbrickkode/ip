import demeter.Task;
import demeter.Event;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @BeforeEach
    void resetIndex() {
        Task.idx = 0;
    }

    @Test
    void printTask_validDates_correctFormatting() {
        Event e = new Event("conference", false, "2026-03-01", "2026-03-03");

        assertEquals(
                "[E][ ] conference (01-03-2026 - 03-03-2026)",
                e.printTask()
        );
    }

    @Test
    void printTask_done_correctFormatting() {
        Event e = new Event("conference", true, "2026-03-01", "2026-03-03");

        assertEquals(
                "[E][X] conference (01-03-2026 - 03-03-2026)",
                e.printTask()
        );
    }

    @Test
    void printToFile_done_correctFormat() {
        Event e = new Event("conference", true, "2026-03-01", "2026-03-03");

        assertEquals(
                "E | 0 | conference | 2026-03-01 - 2026-03-03",
                e.printToFile()
        );
    }

    @Test
    void printToFile_notDone_correctFormat() {
        Event e = new Event("conference", false, "2026-03-01", "2026-03-03");

        assertEquals(
                "E | 1 | conference | 2026-03-01 - 2026-03-03",
                e.printToFile()
        );
    }
}