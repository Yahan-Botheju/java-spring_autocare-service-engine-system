package lk.autocare.vehicle_service_system.web.vehicle.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //create resource not found error handler
    @ExceptionHandler(ResourceNotFoundException.class)
     public ResponseEntity<ErrorMessage> vehicleResourceNotFound(
             ResourceNotFoundException exception,
             WebRequest request
    ){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setStatus(400);
        errorMessage.setMessage(exception.getMessage());
        errorMessage.setDescription(request.getDescription(false));

        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    //common error handler
    @ExceptionHandler(Exception.class)
     public ResponseEntity<ErrorMessage> globalException(
             Exception exception,
             WebRequest request
    ){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setStatus(500);
        errorMessage.setMessage(exception.getMessage());
        errorMessage.setDescription(request.getDescription(false));
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
