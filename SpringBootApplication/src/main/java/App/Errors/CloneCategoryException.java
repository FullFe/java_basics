package App.Errors;

public class CloneCategoryException extends RuntimeException{
    public CloneCategoryException(String categoryName) {
        super(categoryName);
    }
}
