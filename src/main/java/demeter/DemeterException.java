package demeter;

/**
 * Custom exception class used for command parsing and execution errors.
 */
public class DemeterException extends Exception {

    /**
     * Constructs a new DemeterException with the specified error message.
     * @param errorMessage The message describing the exception.
     */
    public DemeterException(String errorMessage) {
        super(errorMessage);
    }
}
