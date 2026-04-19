package lk.autocare.vehicle_service_system.domain.repositories;

import lk.autocare.vehicle_service_system.domain.models.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository {

    //vehicle find by id
    Optional<Vehicle> findById(Long vehicleId);

    //get all vehicle
    List<Vehicle> getAllVehicles();

    //create vehicle
    Vehicle saveVehicle(Vehicle vehicle);
}
