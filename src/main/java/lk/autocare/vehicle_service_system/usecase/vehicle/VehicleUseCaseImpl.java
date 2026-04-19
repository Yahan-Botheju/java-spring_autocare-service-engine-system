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
    public List<Vehicle> getAllVehicles(){
        return vehicleRepository.getAllVehicles();
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


}
