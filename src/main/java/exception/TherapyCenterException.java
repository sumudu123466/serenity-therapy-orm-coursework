package exception;

public class TherapyCenterException extends RuntimeException {

    public TherapyCenterException(String message) {
        super(message);
    }

    public TherapyCenterException(String message, Throwable cause) {
        super(message, cause);
    }
}

