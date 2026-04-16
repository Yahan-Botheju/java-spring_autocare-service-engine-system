package lk.autocare.vehicle_service_system.usecase.customer;

import lk.autocare.vehicle_service_system.domain.models.Customer;

import java.util.List;

public interface CustomerUseCase {

    //get all customers
    List<Customer> getAllCustomers();
}
