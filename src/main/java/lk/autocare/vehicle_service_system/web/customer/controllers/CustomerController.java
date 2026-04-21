package lk.autocare.vehicle_service_system.web.customer.controllers;

import lk.autocare.vehicle_service_system.GlobalResponseHandler.StandardResponse;
import lk.autocare.vehicle_service_system.domain.models.Customer;
import lk.autocare.vehicle_service_system.usecase.customer.CustomerUseCase;
import lk.autocare.vehicle_service_system.web.customer.DTOs.CustomerRequestDTO;
import lk.autocare.vehicle_service_system.web.customer.DTOs.CustomerResponseDTO;
import lk.autocare.vehicle_service_system.web.customer.webMappers.CustomerWebMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/autocare/customers")
@RequiredArgsConstructor
public class CustomerController {

    //inject customer usecase
    private final CustomerUseCase customerUseCase;

    //inject web mapper
    private final CustomerWebMapper customerWebMapper;

    //get all customers
    @GetMapping("/all")
    public ResponseEntity<StandardResponse<List<CustomerResponseDTO>>> getAllCustomers(){
        //get all customers as list from usecase
        List<Customer> customerList = customerUseCase.getAllCustomers();

        //turn all of them as response list
        List<CustomerResponseDTO> responseDTOS = customerList.stream()
                .map(customer -> customerWebMapper.toResponseDTO(customer)).toList();

        //create standard response
        return ResponseEntity.status(HttpStatus.OK).body(
                new StandardResponse<>(
                        200,
                        "Customers details fetched successful",
                        LocalDateTime.now(),
                        responseDTOS)
        );
    }

    //save new customer
    @PostMapping("/register")
    public ResponseEntity<StandardResponse<CustomerResponseDTO>> saveCustomer(
            @RequestBody CustomerRequestDTO customerRequestDTO
    ){
        //turn requestDTO to domain model
        Customer toDomainModel = customerWebMapper.toDomainModel(customerRequestDTO);

        //set model to usecase
        Customer savedCustomer = customerUseCase.saveCustomer(toDomainModel);

        //turn domain model to dto for response
        CustomerResponseDTO responseDTO = customerWebMapper.toResponseDTO(savedCustomer);

        //create custom response
        return ResponseEntity.status(HttpStatus.CREATED).body(
              new StandardResponse<>(
                      201,
                      "Customer registered successful",
                      LocalDateTime.now(),
                      responseDTO)
        );
    }

    //update customer
    @PutMapping("/{customerId}")
    public ResponseEntity<StandardResponse<CustomerResponseDTO>> updateCustomer(
            @PathVariable Long customerId,
            @RequestBody CustomerRequestDTO customerRequestDTO
    ){
        //turn dto to domain model
        Customer customer = customerWebMapper.toDomainModel(customerRequestDTO);

        //set domain model to usecase
        Customer toDomainModel = customerUseCase.updateCustomer(customerId,customer);

        //get that update domain model and turn to dto for response
        CustomerResponseDTO response = customerWebMapper.toResponseDTO(toDomainModel);

        //create custom response
       return ResponseEntity.status(HttpStatus.OK).body(
         new StandardResponse<>(
                 200,
                 "Customer updated successful",
                 LocalDateTime.now(),
                 response)
       );
    }

    //delete customer
    @DeleteMapping("/{customerId}")
    public ResponseEntity<StandardResponse<CustomerResponseDTO>> deleteCustomer(
            @PathVariable Long customerId
    ){
        //set customer id to usecase
        Customer customer = customerUseCase.deleteCustomer(customerId);

        //turn customer into domain model
        CustomerResponseDTO responseDTO = customerWebMapper.toResponseDTO(customer);

        //create response
        return ResponseEntity.status(HttpStatus.OK).body(
                new StandardResponse<>(
                        200,
                        "Customer deleted successful",
                        LocalDateTime.now(),
                        responseDTO)
        );
    }
}
