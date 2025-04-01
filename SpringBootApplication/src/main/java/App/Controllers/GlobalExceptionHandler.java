package App.Controllers;

import App.Entities.ErrorResponse;
import App.Errors.CategoryNotFoundException;
import App.Errors.CloneCategoryException;
import App.Errors.NewsNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NewsNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNewsNotFound(NewsNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCategoriesNotFound(CategoryNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CloneCategoryException.class)
    public ResponseEntity<ErrorResponse> handleCategoriesClone(CloneCategoryException ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.I_AM_A_TEAPOT.value(),
                "I am teapot and already existing category",
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.I_AM_A_TEAPOT);
    }

}
