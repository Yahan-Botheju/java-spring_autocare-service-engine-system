package lk.autocare.vehicle_service_system.web.vehicle.DTOs;

import lk.autocare.vehicle_service_system.domain.models.VehicleFuelType;

import java.time.LocalDate;

public class VehicleResponseDTO {
    private Long vehicleId;
    private String vehicleNumber;
    private String vehicleModel;
    private VehicleFuelType vehicleFuelType;
    private LocalDate lastServiceDate;
    private LocalDate nextServiceDate;
    private int mileage;
    private Long customerId;
}
