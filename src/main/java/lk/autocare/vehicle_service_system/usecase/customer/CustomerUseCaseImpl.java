package lk.autocare.vehicle_service_system.usecase.customer;

import lk.autocare.vehicle_service_system.domain.models.Customer;
import lk.autocare.vehicle_service_system.domain.models.Vehicle;
import lk.autocare.vehicle_service_system.domain.models.VehicleUpdateResult;
import lk.autocare.vehicle_service_system.domain.repositories.CustomerRepository;
import lk.autocare.vehicle_service_system.GlobalExceptionHandler.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CustomerUseCaseImpl implements  CustomerUseCase {

    //inject customer domain repo as initiating bean config
    private final CustomerRepository customerRepository;

    //get all customers
    @Override
    public List<Customer> getAllCustomers(int page, int size) {
        //get all customers
        List<Customer> customers = customerRepository.getAllCustomers(page, size);

        return customers.stream().map(customer -> {
            customer.getVehicles().forEach(Vehicle::updateNextServiceDate);
            return customer;
        }).toList();

    }

    //save new customer
    @Override
    public Customer saveCustomer(Customer customer){
        return customerRepository.saveCustomer(customer);
    }

    //update customer
    @Override
    public Customer updateCustomer(Long customerId, Customer customer){

        //check customer availability then throw error to exception handler
        customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found" + " " + customerId));

        //set values to domain repo to save in db
        return customerRepository.updateCustomer(customerId,customer);
    }

    //delete customer
    @Override
    public void deleteCustomer(Long customerId){

        //check customer availability then throw error to exception handler
        if(customerRepository.findById(customerId).isEmpty()){
            throw new ResourceNotFoundException("Customer not found(usecase)" + " " + customerId);
        }

        //set customer to domain repo to delete
       customerRepository.deleteCustomer(customerId);
    }
}
