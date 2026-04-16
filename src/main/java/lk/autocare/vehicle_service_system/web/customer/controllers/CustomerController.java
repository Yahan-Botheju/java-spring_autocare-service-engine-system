package lk.autocare.vehicle_service_system.web.customer.controllers;

import lk.autocare.vehicle_service_system.domain.models.Customer;
import lk.autocare.vehicle_service_system.usecase.customer.CustomerUseCase;
import lk.autocare.vehicle_service_system.web.customer.DTOs.CustomerResponseDTO;
import lk.autocare.vehicle_service_system.web.customer.webMappers.CustomerWebMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/autocare/")
@RequiredArgsConstructor
public class CustomerController {

    //inject customer usecase
    private final CustomerUseCase customerUseCase;

    //inject web mapper
    private final CustomerWebMapper customerWebMapper;

    @GetMapping("/all")
    public List<CustomerResponseDTO> getAllCustomers(){
        List<Customer> customerList = customerUseCase.getAllCustomers();
        
        return customerList.stream().map(e -> customerWebMapper.toResponseDTO(e)).toList();
    }
}
