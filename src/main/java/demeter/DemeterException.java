package demeter;

public class DemeterException extends Exception {

    /**
     * Constructs a new DemeterException with the specified error message.
     * @param errorMessage The message describing the exception.
     */
    public DemeterException(String errorMessage) {
        super(errorMessage);
    }
}
