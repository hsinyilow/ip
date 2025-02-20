package astra.system;

/**
 * Handles chatbot specific errors.
 */
public class AstraException extends Exception {
    /**
     * Returns chatbot specific error.
     *
     * @param errorMessage Error message to display.
     */
    public AstraException(String errorMessage) {
        super(errorMessage);
    }
}
