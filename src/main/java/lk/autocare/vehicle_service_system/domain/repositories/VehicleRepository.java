package lk.autocare.vehicle_service_system.domain.repositories;

import lk.autocare.vehicle_service_system.domain.models.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository {

    //vehicle find by id
    Optional<Vehicle> findById(Long vehicleId);

    //get all vehicle
    List<Vehicle> getAllVehicles(int page, int size);

    //create vehicle
    Vehicle saveVehicle(Vehicle vehicle);

    //update vehicle details
    Vehicle updateVehicle(Long vehicleId, Vehicle vehicle);

    //delete vehicle
    void deleteVehicle(Long vehicleId);
}
