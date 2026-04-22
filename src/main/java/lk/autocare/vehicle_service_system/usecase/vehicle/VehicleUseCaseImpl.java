package lk.autocare.vehicle_service_system.usecase.vehicle;

import lk.autocare.vehicle_service_system.GlobalExceptionHandler.ResourceNotFoundException;
import lk.autocare.vehicle_service_system.domain.models.Customer;
import lk.autocare.vehicle_service_system.domain.models.Vehicle;
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
    public List<VehicleUpdateResult> getAllVehicles(){

        //get all vehicles
        List<Vehicle> vehicles = vehicleRepository.getAllVehicles();

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

        //save vehicle details through domain repo
        Vehicle savedVehicle = vehicleRepository.saveVehicle(vehicle);

        //use helper method
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
        if(vehicleRepository.findById(vehicleId).isEmpty()){
            throw new ResourceNotFoundException("Vehicle id not found" + " " + vehicleId);
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
    public VehicleUpdateResult deleteVehicle(Long vehicleId){
        //check vehicle availability by ID
        if(vehicleRepository.findById(vehicleId).isEmpty()){
            throw new ResourceNotFoundException("Vehicle id not found" + " " + vehicleId);
        }
        //set id to domain repo to remove from db
        Vehicle toDomainModel =  vehicleRepository.deleteVehicle(vehicleId);

        //check related vehicle owner
        Customer customer = getCustomerDetails(toDomainModel.getCustomerId());

        //return both vehicl model and customer
        return new VehicleUpdateResult(toDomainModel, customer);
    }
}














