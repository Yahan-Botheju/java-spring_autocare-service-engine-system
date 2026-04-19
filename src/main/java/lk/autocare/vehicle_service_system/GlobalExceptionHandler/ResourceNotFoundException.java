package lk.autocare.vehicle_service_system.GlobalExceptionHandler;

public class ResourceNotFoundException extends  RuntimeException{
    public ResourceNotFoundException(String message){
        super(message);
    }
}
