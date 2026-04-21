package lk.autocare.vehicle_service_system.usecase.customer;

import lk.autocare.vehicle_service_system.domain.models.Customer;
import lk.autocare.vehicle_service_system.domain.repositories.CustomerRepository;
import lk.autocare.vehicle_service_system.GlobalExceptionHandler.ResourceNotFoundException;
import lk.autocare.vehicle_service_system.web.customer.DTOs.CustomerResponseDTO;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CustomerUseCaseImpl implements  CustomerUseCase {

    //inject customer domain repo as initiating bean config
    private final CustomerRepository customerRepository;

    //get all customers
    @Override
    public List<Customer> getAllCustomers(){
        return customerRepository.getAllCustomers();
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
    public Customer deleteCustomer(Long customerId){

        //check customer availability then throw error to exception handler
        if(customerRepository.findById(customerId).isEmpty()){
            throw new ResourceNotFoundException("Customer not found(usecase)" + " " + customerId);
        }

        //set customer to domain repo to delete
       return customerRepository.deleteCustomer(customerId);
    }
}
