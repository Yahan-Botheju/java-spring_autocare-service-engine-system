package lk.autocare.vehicle_service_system.usecase.customer;

import lk.autocare.vehicle_service_system.domain.models.Customer;
import lk.autocare.vehicle_service_system.domain.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

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
    public void saveCustomer(Customer customer){
        customerRepository.saveCustomer(customer);
    }

    //update customer
    @Override
    public void updateCustomer(Long customerId, Customer customer){

        //check customer availability then throw error to exception handler
        customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found" + " " + customerId));
        //customer found then save in db
        customerRepository.updateCustomer(customerId,customer);
    }

    //delete customer
    @Override
    public void deleteCustomer(Long customerId){
        customerRepository.deleteCustomer(customerId);
    }
}
