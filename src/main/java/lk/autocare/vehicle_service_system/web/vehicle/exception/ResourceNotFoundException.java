package lk.autocare.vehicle_service_system.web.vehicle.exception;

public class ResourceNotFoundException extends RuntimeException {
  public ResourceNotFoundException(String message) {
    super(message);
  }
}
