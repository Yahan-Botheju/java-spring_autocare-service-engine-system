package lk.autocare.vehicle_service_system.usecase.vehicle;

import lk.autocare.vehicle_service_system.domain.models.Vehicle;
import lk.autocare.vehicle_service_system.domain.repositories.CustomerRepository;
import lk.autocare.vehicle_service_system.domain.repositories.VehicleRepository;
import lk.autocare.vehicle_service_system.web.vehicle.webMappers.VehicleWebMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class VehicleUseCaseImpl implements  VehicleUseCase{

    //inject domain repo as bean this class
    private final VehicleRepository vehicleRepository;

    //inject customer domain repo for get customer details
    private final CustomerRepository customerRepository;

    //inject web mapper
    private final VehicleWebMapper vehicleWebMapper;

    //get all vehicle
    public List<Vehicle> getAllVehicles(){
        return vehicleRepository.getAllVehicles();
    }

    //create vehicle
    public Vehicle saveVehicle(Vehicle vehicle){

        vehicleRepository.saveVehicle(vehicle);
        return vehicle;
    }


}
