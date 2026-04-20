package lk.autocare.vehicle_service_system.usecase.vehicle;

import lk.autocare.vehicle_service_system.GlobalExceptionHandler.ResourceNotFoundException;
import lk.autocare.vehicle_service_system.domain.models.Customer;
import lk.autocare.vehicle_service_system.domain.models.Vehicle;
import lk.autocare.vehicle_service_system.domain.repositories.CustomerRepository;
import lk.autocare.vehicle_service_system.domain.repositories.VehicleRepository;
import lk.autocare.vehicle_service_system.web.vehicle.DTOs.VehicleResponseDTO;
import lk.autocare.vehicle_service_system.web.vehicle.webMappers.VehicleWebMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class VehicleUseCaseImpl implements  VehicleUseCase{

    //inject domain repo as bean this class
    private final VehicleRepository vehicleRepository;

    //inject customer domain repo for get customer details
    private final CustomerRepository customerRepository;

    //inject web mapper
    private final VehicleWebMapper vehicleWebMapper;


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
    public List<VehicleResponseDTO> getAllVehicles(){
        //get all vehicles
        List<Vehicle> allVehicles = vehicleRepository.getAllVehicles();

        //set customer name and id related to vehicle and return as list of each vehicles
        return allVehicles.stream().map(vehicles -> {

            //set vehicles along with customer id using custom method
            Customer customer = getCustomerDetails(vehicles.getCustomerId());

            //turn dto and set it to customer name, then return
            VehicleResponseDTO toResponseDTO = vehicleWebMapper.toResponseDTO(vehicles);
                                toResponseDTO.setCustomerName(customer.getCustomerName());

             return toResponseDTO;

        }).toList();
    }

    //create vehicle
    @Override
    public VehicleResponseDTO saveVehicle(Vehicle vehicle){

        //save vehicle details through domain repo
        Vehicle savedVehicle = vehicleRepository.saveVehicle(vehicle);

        //get related customer id for saved vehicle(req)
        Customer customer = getCustomerDetails(savedVehicle.getCustomerId());

        //turn into response that vehicle details with customer name and ID
        VehicleResponseDTO turnToResponseDTO = vehicleWebMapper.toResponseDTO(savedVehicle);
                           turnToResponseDTO.setCustomerName(customer.getCustomerName());

        //return response object
        return turnToResponseDTO;
    }

    //update vehicle details
    @Override
    public VehicleResponseDTO updateVehicle(Long vehicleId, Vehicle vehicle){
        //check vehicle availability
        if(vehicleRepository.findById(vehicleId).isEmpty()){
            throw new ResourceNotFoundException("Vehicle id not found" + " " + vehicleId);
        }

        //set to update method in repo for update values
        Vehicle updatedVehicle = vehicleRepository.updateVehicle(vehicleId, vehicle);

        //updated model check with customer ID
        Customer customer = getCustomerDetails(updatedVehicle.getCustomerId());

        //turn to DTO with customer name and id included
        VehicleResponseDTO turnToResponseDTO = vehicleWebMapper.toResponseDTO(updatedVehicle);
                           turnToResponseDTO.setCustomerName(customer.getCustomerName());

        // return response dto
        return turnToResponseDTO;
    }


    //delete vehicle
    @Override
    public VehicleResponseDTO deleteVehicle(Long vehicleId){

        if(vehicleRepository.findById(vehicleId).isEmpty()){
            throw new ResourceNotFoundException("Vehicle id not found" + " " + vehicleId);
        }

        Vehicle vehicle = vehicleRepository.deleteVehicle(vehicleId);

        Customer customer = getCustomerDetails(vehicle.getCustomerId());

        VehicleResponseDTO turnToResponseDTO = vehicleWebMapper.toResponseDTO(vehicle);
                           turnToResponseDTO.setCustomerName(customer.getCustomerName());

                           return turnToResponseDTO;
    }
}














