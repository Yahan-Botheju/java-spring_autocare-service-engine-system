package lk.autocare.vehicle_service_system.usecase.vehicle;

import lk.autocare.vehicle_service_system.domain.models.Vehicle;
import lk.autocare.vehicle_service_system.domain.models.VehicleUpdateResult;
import lk.autocare.vehicle_service_system.web.vehicle.DTOs.VehicleResponseDTO;

import java.util.List;

public interface VehicleUseCase {

    //get all vehicle
    List<VehicleUpdateResult> getAllVehicles();

    //create vehicle
    VehicleUpdateResult saveVehicle(Vehicle vehicle);

    //update vehicle details
    VehicleUpdateResult updateVehicle(Long vehicleId, Vehicle vehicle);

    //delete vehicle
    VehicleUpdateResult deleteVehicle(Long vehicleId);
}
