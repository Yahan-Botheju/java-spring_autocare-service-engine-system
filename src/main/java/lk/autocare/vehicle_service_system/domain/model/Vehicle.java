package lk.autocare.vehicle_service_system.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {

    private Long vehicleId;
    private String vehicleNumber;
    private String vehicleModel;
    //enum
    private VehicleFuelType vehicleFuelType;
    private LocalDate lastServiceDate;
    private LocalDate nextServiceDate;
    private int mileage;
    //customer object
    private Customer customer;
}
