package lk.autocare.vehicle_service_system.domain.repositories;

import lk.autocare.vehicle_service_system.domain.models.Customer;

import java.util.List;

public interface CustomerRepository {

    //get all customers
    List<Customer> getAllCustomers();

    //save new customer
    void saveCustomer(Customer customer);
}
