package lab.exception;


public class DatabaseError extends RuntimeException {
    public DatabaseError(String message) {
        super(message);
    }

    public DatabaseError(String message, Throwable cause) {
        super(message, cause);
    }

    public DatabaseError(Throwable cause) {
        super(cause);
    }
}
