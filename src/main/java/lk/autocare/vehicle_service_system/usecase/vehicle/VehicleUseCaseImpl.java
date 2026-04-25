package lk.autocare.vehicle_service_system.usecase.vehicle;

import lk.autocare.vehicle_service_system.GlobalExceptionHandler.ResourceNotFoundException;
import lk.autocare.vehicle_service_system.domain.models.Customer;
import lk.autocare.vehicle_service_system.domain.models.Vehicle;
import lk.autocare.vehicle_service_system.domain.models.VehicleServiceStatus;
import lk.autocare.vehicle_service_system.domain.models.VehicleUpdateResult;
import lk.autocare.vehicle_service_system.domain.repositories.CustomerRepository;
import lk.autocare.vehicle_service_system.domain.repositories.VehicleRepository;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
public class VehicleUseCaseImpl implements  VehicleUseCase{

    //inject domain repo as bean this class
    private final VehicleRepository vehicleRepository;

    //inject customer domain repo for get customer details
    private final CustomerRepository customerRepository;



    /*   HELPER METHODS   */


    //create private method get customer details for response, use for other methods
    private Customer getCustomerDetails(Long customerId){
        //check customer availability and throw an error if missing
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found" + " " +  customerId));
    }


    /*   PUBLIC METHODS   */

    //get all vehicle
    @Override
    public List<VehicleUpdateResult> getAllVehicles(int page, int size){

        //get all vehicles
        List<Vehicle> vehicles = vehicleRepository.getAllVehicles(page, size);

        //return them assigning customer model and vehicle model
        return vehicles.stream().map(vehicle -> {
            Customer customer = getCustomerDetails(vehicle.getCustomerId());
            //call helper method for update next service date
            vehicle.updateNextServiceDate();
            return new VehicleUpdateResult(vehicle, customer);
        }).toList();
    }

    //create vehicle
    @Override
    public VehicleUpdateResult saveVehicle(Vehicle vehicle){

        //check customer ID, for customer availability
        vehicleRepository.findById(vehicle.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found" + " " +  vehicle.getCustomerId()));

        //call default service status set method (DOMAIN MODEL METHOD)
        vehicle.setDefaultVehicleServiceStatus();

        //save vehicle details through domain repo
        Vehicle savedVehicle = vehicleRepository.saveVehicle(vehicle);

        //use helper method (DOMAIN MODEL METHOD)
        vehicle.updateNextServiceDate();

        //get related customer id for saved vehicle(req)
        Customer customer = getCustomerDetails(savedVehicle.getCustomerId());

        //return both customer and vehicle models
        return new VehicleUpdateResult(savedVehicle,customer);
    }

    //update vehicle details
    @Override
    public VehicleUpdateResult updateVehicle(Long vehicleId, Vehicle vehicle){
        //check vehicle availability
        Vehicle existingVehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() ->  new ResourceNotFoundException("Vehicle not found" + " " +  vehicleId));

        //set service status complete vehicle, disable to update
        if (existingVehicle.getVehicleServiceStatus() == VehicleServiceStatus.COMPLETED){
            throw new ResourceNotFoundException("Vehicle service already completed, unable to edit vehicle details");
        }

        //set to update method in repo for update values
        Vehicle updatedVehicle = vehicleRepository.updateVehicle(vehicleId, vehicle);

        //use helper method
        vehicle.updateNextServiceDate();

        //updated model check with customer ID
        Customer customer = getCustomerDetails(updatedVehicle.getCustomerId());

        // return both customer and vehicle model
        return new VehicleUpdateResult(updatedVehicle, customer);
    }


    //delete vehicle
    @Override
    public void deleteVehicle(Long vehicleId){
        //check vehicle availability by ID
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle id not found" + " " +  vehicleId));

        //use helper method (DOMAIN MODEL METHOD)
        vehicle.disableVehicleUpdate();

        //set id to domain repo to remove from db
        vehicleRepository.deleteVehicle(vehicleId);
    }
}














