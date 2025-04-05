package App.Errors;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(long id) {
        super("Category with id " +id + " not found ");
    }
    public CategoryNotFoundException() {
        super("Category not found ");
    }
}
