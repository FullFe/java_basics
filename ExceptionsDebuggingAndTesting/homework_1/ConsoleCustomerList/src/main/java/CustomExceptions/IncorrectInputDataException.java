package CustomExceptions;

public class IncorrectInputDataException extends RuntimeException {
    public IncorrectInputDataException(String msg) {
        super(msg);
    }
}
