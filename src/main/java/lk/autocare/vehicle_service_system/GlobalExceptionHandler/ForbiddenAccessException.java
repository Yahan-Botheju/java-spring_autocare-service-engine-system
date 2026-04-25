package lk.autocare.vehicle_service_system.GlobalExceptionHandler;

public class ForbiddenAccessException extends RuntimeException {
    public ForbiddenAccessException(String message) {
        super(message);
    }
}
