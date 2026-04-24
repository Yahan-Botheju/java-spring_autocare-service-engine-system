package lk.autocare.vehicle_service_system.GlobalExceptionHandler;

public class ServiceAlreadyCompletedException extends RuntimeException {
    public ServiceAlreadyCompletedException(String message) {
        super(message);
    }
}
