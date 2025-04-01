package App.Errors;

public class NewsNotFoundException extends RuntimeException {
    public NewsNotFoundException(long id) {
        super("News with id " + id + " not found");
    }
}
