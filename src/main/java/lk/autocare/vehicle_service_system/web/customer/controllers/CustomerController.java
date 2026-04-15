package lk.autocare.vehicle_service_system.web.customer.controllers;

import lk.autocare.vehicle_service_system.usecase.customer.CustomerUseCase;
import lk.autocare.vehicle_service_system.web.customer.webMappers.CustomerWebMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/autocare/")
@RequiredArgsConstructor
public class CustomerController {

    //inject customer usecase
    private final CustomerUseCase customerUseCase;

    //inject web mapper
    private final CustomerWebMapper customerWebMapper;
}
