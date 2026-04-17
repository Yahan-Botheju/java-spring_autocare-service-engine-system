package lk.autocare.vehicle_service_system.domain.repositories;

import lk.autocare.vehicle_service_system.domain.models.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

    //find customer by id
    Optional<Customer> findById(Long customerId);

    //get all customers
    List<Customer> getAllCustomers();

    //save new customer
    void saveCustomer(Customer customer);

    //update customer
    void updateCustomer(Long customerId, Customer customer);

    //delete customer
    void deleteCustomer(Long customerId);
}
