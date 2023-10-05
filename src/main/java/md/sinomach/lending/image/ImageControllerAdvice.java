package md.sinomach.lending.image;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice(assignableTypes = {ImageController.class})
public class ImageControllerAdvice {
    @ExceptionHandler(ImageNotFoundException.class)
    public ResponseEntity<String> imageNotFoundHandle(ImageNotFoundException e, WebRequest r) {
        return new ResponseEntity<>("Image with ID " + e.getId() + " not found", HttpStatus.NOT_FOUND);
    }
}
