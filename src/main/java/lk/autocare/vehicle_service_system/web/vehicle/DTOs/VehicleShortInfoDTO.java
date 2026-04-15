package lk.autocare.vehicle_service_system.web.customer.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerShortInfoDTO {
    private Long vehicleId;
    private String vehicleNumber;
    private String vehicleModel;
    private LocalDate lastServiceDate;
    private LocalDate nextServiceDate;
    private int mileage;
}
