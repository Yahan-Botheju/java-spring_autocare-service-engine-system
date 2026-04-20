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
        //just call and return get all vehicles
        return vehicleUseCase.getAllVehicles();
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

    //update vehicle details
    @PutMapping("/{vehicleId}")
    public ResponseEntity<VehicleResponseDTO> updateVehicle(
            @PathVariable Long vehicleId,
            @RequestBody VehicleRequestDTO vehicleRequestDTO
    ){
        //turn to domain model
        Vehicle vehicleDomainModel = vehicleWebMapper.toDomainModel(vehicleRequestDTO);

        //set values to usecase for update
        VehicleResponseDTO toResponse = vehicleUseCase.updateVehicle(vehicleId, vehicleDomainModel);

        //return response with updated values
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse);
    }

    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<VehicleResponseDTO> deleteVehicles(
            @PathVariable Long vehicleId
    ){
        VehicleResponseDTO vehicle = vehicleUseCase.deleteVehicle(vehicleId);

        return ResponseEntity.status(HttpStatus.OK).body(vehicle);

    }
}
