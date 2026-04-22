package lk.autocare.vehicle_service_system.web.vehicle.DTOs;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lk.autocare.vehicle_service_system.domain.models.VehicleFuelType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleRequestDTO {
    @NotBlank(message = "Vehicle number cannot be empty")
    private String vehicleNumber;
    @NotBlank(message = "Vehicle model cannot be empty")
    private String vehicleModel;
    @NotNull(message = "Fuel type cannot be empty")
    private VehicleFuelType vehicleFuelType;
    @Min(value = 10, message = "At least vehicle should be 10 miles ")
    private int mileage;
    @NotNull(message = "Last service date cannot be empty")
    @PastOrPresent(message = "Date should not be present")
    private LocalDate lastServiceDate;
    @NotNull(message = "Customer ID cannot be empty")
    private Long customerId;
}
