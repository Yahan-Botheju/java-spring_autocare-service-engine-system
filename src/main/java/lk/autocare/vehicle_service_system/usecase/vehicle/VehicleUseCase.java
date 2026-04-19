package lk.autocare.vehicle_service_system.usecase.vehicle;

import lk.autocare.vehicle_service_system.domain.models.Vehicle;
import lk.autocare.vehicle_service_system.web.vehicle.DTOs.VehicleResponseDTO;

import java.util.List;

public interface VehicleUseCase {

    //get all vehicle
    List<Vehicle> getAllVehicles();

    //create vehicle
    VehicleResponseDTO saveVehicle(Vehicle vehicle);
}
