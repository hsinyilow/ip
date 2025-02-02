package astra.system;

public class AstraException extends Exception {
    /**
     * Returns chatbot specific error.
     * @param errorMessage Error message to display.
     */
    public AstraException(String errorMessage) {
        super(errorMessage);
    }
}
