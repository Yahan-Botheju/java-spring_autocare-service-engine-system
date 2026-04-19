package lk.autocare.vehicle_service_system.usecase.vehicle;

import lk.autocare.vehicle_service_system.domain.models.Vehicle;

import java.util.List;

public interface VehicleUseCase {

    //get all vehicle
    List<Vehicle> getAllVehicles();
}
