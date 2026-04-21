package lk.autocare.vehicle_service_system.GlobalResponseHandler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StandardResponse<T> {
    private int statusCode;
    private String message;
    private LocalDateTime timestamp;
    private T data;


}
