package lk.autocare.vehicle_service_system.usecase.customer;

import lk.autocare.vehicle_service_system.domain.models.Customer;
import lk.autocare.vehicle_service_system.domain.repositories.CustomerRepository;
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
    public void saveCustomer(Customer customer){
        customerRepository.saveCustomer(customer);
    }

    //update customer
    @Override
    public void updateCustomer(Long customerId, Customer customer){
        customerRepository.updateCustomer(customerId,customer);
    }
}
