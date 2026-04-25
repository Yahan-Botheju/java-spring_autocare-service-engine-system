package lk.autocare.vehicle_service_system.usecase.vehicle;

import lk.autocare.vehicle_service_system.domain.models.Vehicle;
import lk.autocare.vehicle_service_system.domain.models.VehicleUpdateResult;

import java.util.List;

public interface VehicleUseCase {

    //get all vehicle
    List<VehicleUpdateResult> getAllVehicles(int page, int size);

    //create vehicle
    VehicleUpdateResult saveVehicle(Vehicle vehicle);

    //update vehicle details
    VehicleUpdateResult updateVehicle(Long vehicleId, Vehicle vehicle);

    //delete vehicle
    void deleteVehicle(Long vehicleId);
}
