package lk.autocare.vehicle_service_system.web.vehicle.controllers;

import lk.autocare.vehicle_service_system.domain.models.Vehicle;
import lk.autocare.vehicle_service_system.usecase.vehicle.VehicleUseCase;
import lk.autocare.vehicle_service_system.web.vehicle.DTOs.VehicleRequestDTO;
import lk.autocare.vehicle_service_system.web.vehicle.DTOs.VehicleResponseDTO;
import lk.autocare.vehicle_service_system.web.vehicle.webMappers.VehicleWebMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/autocare/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    //inject use case
    private final VehicleUseCase vehicleUseCase;

    //inject web mapper
    private final VehicleWebMapper vehicleWebMapper;

    //get all vehicles
    @GetMapping("/all")
    public List<VehicleResponseDTO> allVehicles() {
        //get all details as domain model
        List<Vehicle> vehicles = vehicleUseCase.getAllVehicles();
        //domain model to response dto
        return vehicles.stream().map(e -> vehicleWebMapper.toResponseDTO(e)).toList();
    }

    //register vehicle
    @PostMapping("/register")
    public ResponseEntity<VehicleResponseDTO> saveVehicle(
            @RequestBody VehicleRequestDTO vehicleRequestDTO
            ){
        //requestDTO to domain model
        Vehicle vehicleDomainModel = vehicleWebMapper.toDomainModel(vehicleRequestDTO);

        //get domain model and turn into responseDTO
        VehicleResponseDTO response =  vehicleUseCase.saveVehicle(vehicleDomainModel);

        return  ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
