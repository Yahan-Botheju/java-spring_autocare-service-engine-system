package lk.autocare.vehicle_service_system.web.customer.controllers;

import lk.autocare.vehicle_service_system.domain.models.Customer;
import lk.autocare.vehicle_service_system.usecase.customer.CustomerUseCase;
import lk.autocare.vehicle_service_system.web.customer.DTOs.CustomerRequestDTO;
import lk.autocare.vehicle_service_system.web.customer.DTOs.CustomerResponseDTO;
import lk.autocare.vehicle_service_system.web.customer.webMappers.CustomerWebMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/autocare/customer")
@RequiredArgsConstructor
public class CustomerController {

    //inject customer usecase
    private final CustomerUseCase customerUseCase;

    //inject web mapper
    private final CustomerWebMapper customerWebMapper;

    @GetMapping("/all")
    public List<CustomerResponseDTO> getAllCustomers(){
        List<Customer> customerList = customerUseCase.getAllCustomers();
        
        return customerList.stream().map(customer -> customerWebMapper.toResponseDTO(customer)).toList();
    }

    //save new customer
    @PatchMapping("/register")
    public ResponseEntity<String> saveCustomer(
            @RequestBody CustomerRequestDTO customerRequestDTO
    ){
        //turn requestDTO to domain model
        Customer toDomainModel = customerWebMapper.toDomainModel(customerRequestDTO);
        //set model to usecase
        customerUseCase.saveCustomer(toDomainModel);

        return ResponseEntity.status(HttpStatus.CREATED).body("Customer registered successfully");
    }

    //update customer
    @PutMapping("/{customerId}")
    public ResponseEntity<String> updateCustomer(
            @PathVariable Long customerId,
            @RequestBody CustomerRequestDTO customerRequestDTO
    ){
        //turn request dto to domain model
        Customer toDomainModel = customerWebMapper.toDomainModel(customerRequestDTO);

        //set values to usecase
       customerUseCase.updateCustomer(customerId, toDomainModel);

       return ResponseEntity.status(HttpStatus.CREATED).body("Customer updated successfully");
    }

    //delete customer
    @DeleteMapping("/{customerId}")
    public ResponseEntity<String> deleteCustomer(
            @PathVariable Long customerId
    ){
        //directly set value to usecase
        customerUseCase.deleteCustomer(customerId);

        return ResponseEntity.status(HttpStatus.OK).body("Customer deleted successfully");
    }
}
