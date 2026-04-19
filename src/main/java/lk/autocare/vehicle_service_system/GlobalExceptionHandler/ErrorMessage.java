package lk.autocare.vehicle_service_system.GlobalExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {
    private LocalDateTime timestamp;
    private int status;
    private String message;
    private String description;
}
