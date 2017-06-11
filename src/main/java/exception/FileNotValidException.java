package exception;

public class FileNotValidException extends RuntimeException {

    public FileNotValidException(Throwable cause) {
        super("File is not valid", cause);
    }
}
