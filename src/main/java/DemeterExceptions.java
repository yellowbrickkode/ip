public class DemeterExceptions extends Exception {
    public DemeterExceptions(String errorMessage) {
        super("Oh no! " + errorMessage);
    }
}
