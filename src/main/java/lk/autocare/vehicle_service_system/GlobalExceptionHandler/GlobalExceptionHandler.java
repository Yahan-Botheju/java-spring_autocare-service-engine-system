package lk.autocare.vehicle_service_system.GlobalExceptionHandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    //resource not found error handler
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> resourceNotFound(
            ResourceNotFoundException ex,
            WebRequest request

    ){
        //add logback
        log.error("Resource not found{}", ex.getMessage(), ex);

        ErrorMessage  errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setStatus(404);
        errorMessage.setMessage(ex.getMessage());
        errorMessage.setDescription(request.getDescription(false));

        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    //generic error handler
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> globalException(
            Exception ex,
            WebRequest request
    ){

        log.error("Internal server error", ex);

        ErrorMessage  errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setStatus(500);
        errorMessage.setMessage(ex.getMessage());
        errorMessage.setDescription(request.getDescription(false));
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
