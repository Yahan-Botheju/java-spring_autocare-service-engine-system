package lk.autocare.vehicle_service_system.usecase.customer;

import lk.autocare.vehicle_service_system.domain.models.Customer;
import lk.autocare.vehicle_service_system.domain.models.Vehicle;
import lk.autocare.vehicle_service_system.web.customer.DTOs.CustomerResponseDTO;

import java.util.List;

public interface CustomerUseCase {

    //get all customers
    List<Customer> getAllCustomers();

    //save new customer
    Customer saveCustomer(Customer customer);

    //update customer
    Customer updateCustomer(Long customerId, Customer customer);

    //delete customer
    Customer deleteCustomer(Long customerId);
}
