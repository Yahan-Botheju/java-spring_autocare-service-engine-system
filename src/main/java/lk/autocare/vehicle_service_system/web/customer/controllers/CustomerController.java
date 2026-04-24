package lk.autocare.vehicle_service_system.web.customer.controllers;

import lk.autocare.vehicle_service_system.GlobalResponseHandler.StandardResponse;
import lk.autocare.vehicle_service_system.domain.models.Customer;
import lk.autocare.vehicle_service_system.usecase.customer.CustomerUseCase;
import lk.autocare.vehicle_service_system.web.customer.DTOs.CustomerRequestDTO;
import lk.autocare.vehicle_service_system.web.customer.DTOs.CustomerResponseDTO;
import lk.autocare.vehicle_service_system.web.customer.webMappers.CustomerWebMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/autocare/customers")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

    //inject customer usecase
    private final CustomerUseCase customerUseCase;

    //inject web mapper
    private final CustomerWebMapper customerWebMapper;

    //get all customers
    @GetMapping("/all")
    public ResponseEntity<StandardResponse<List<CustomerResponseDTO>>> getAllCustomers(){

        //add logger
        log.info("Request received for all customers");

        //get all customers as list from usecase
        List<Customer> customerList = customerUseCase.getAllCustomers();

        //turn all of them as response list
        List<CustomerResponseDTO> responseDTOS = customerList.stream()
                .map(customer -> customerWebMapper.toResponseDTO(customer)).toList();

        //create standard response
        return ResponseEntity.ok(new StandardResponse<>(
                        200,
                        "Customers details fetched successfully",
                        LocalDateTime.now(),
                        responseDTOS)
        );
    }

    //save new customer
    @PostMapping("/register")
    public ResponseEntity<StandardResponse<CustomerResponseDTO>> saveCustomer(
            @RequestBody CustomerRequestDTO customerRequestDTO
    ){

        log.info("Request received for saving customer");

        //turn requestDTO to domain model
        Customer toDomainModel = customerWebMapper.toDomainModel(customerRequestDTO);

        //set model to usecase
        Customer savedCustomer = customerUseCase.saveCustomer(toDomainModel);

        //turn domain model to dto for response
        CustomerResponseDTO responseDTO = customerWebMapper.toResponseDTO(savedCustomer);

        log.info("Saving customer successful..!!, id={}", responseDTO.getCustomerId());

        //create custom response
        return ResponseEntity.created(URI.create("/api/v1/autocare/customers/" + responseDTO.getCustomerId()))
                .body(new StandardResponse<>(
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
        log.info("Request received for updating customer");

        //turn dto to domain model
        Customer customer = customerWebMapper.toDomainModel(customerRequestDTO);

        //set domain model to usecase
        Customer toDomainModel = customerUseCase.updateCustomer(customerId,customer);

        //get that update domain model and turn to dto for response
        CustomerResponseDTO response = customerWebMapper.toResponseDTO(toDomainModel);

        log.info("Updating customer successful.!.!, id={}", customerId);

        //create custom response
       return ResponseEntity.ok(
               new StandardResponse<>(
                 200,
                 "Customer updated successful",
                 LocalDateTime.now(),
                 response)
       );
    }

    //delete customer
    @DeleteMapping("/{customerId}")
    public ResponseEntity<String> deleteCustomer(
            @PathVariable Long customerId
    ){

        log.info("Request received for deleting customer");

        //set customer id to usecase
        customerUseCase.deleteCustomer(customerId);

        log.info("Deleting customer successful..!!{}", customerId);

        return ResponseEntity.noContent().build();

    }
}
