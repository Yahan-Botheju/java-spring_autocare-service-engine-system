package lk.autocare.vehicle_service_system.web.customer.exception;

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
