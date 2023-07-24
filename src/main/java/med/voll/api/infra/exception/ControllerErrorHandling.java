package med.voll.api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import med.voll.api.infra.exception.dto.ValidationErrorData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerErrorHandling {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleError404() {
        return ResponseEntity.notFound().build();
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleError400(MethodArgumentNotValidException exception) {
        return ResponseEntity.badRequest().body(exception.getFieldErrors().stream().map(ValidationErrorData::new).toList());
    }

    @ExceptionHandler(AppointmentValidationException.class)
    public ResponseEntity handleValidationExceptions(AppointmentValidationException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }


}
