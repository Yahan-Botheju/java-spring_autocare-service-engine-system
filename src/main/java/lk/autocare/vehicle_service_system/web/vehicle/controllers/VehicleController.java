package lk.autocare.vehicle_service_system.web.vehicle.controllers;

import lk.autocare.vehicle_service_system.GlobalResponseHandler.StandardResponse;
import lk.autocare.vehicle_service_system.domain.models.Vehicle;
import lk.autocare.vehicle_service_system.domain.models.VehicleUpdateResult;
import lk.autocare.vehicle_service_system.usecase.vehicle.VehicleUseCase;
import lk.autocare.vehicle_service_system.web.vehicle.DTOs.VehicleRequestDTO;
import lk.autocare.vehicle_service_system.web.vehicle.DTOs.VehicleResponseDTO;
import lk.autocare.vehicle_service_system.web.vehicle.webMappers.VehicleWebMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
    public ResponseEntity<StandardResponse<List<VehicleResponseDTO>>> allVehicles() {
        //get all vehicles
        List<VehicleUpdateResult> vehiclesResults = vehicleUseCase.getAllVehicles();

        //turn to list
        List<VehicleResponseDTO> responseDTOS = vehiclesResults.stream()
                 .map(vehicleResult -> vehicleWebMapper.customResponseDTO(vehicleResult)).toList();

        //return response
        return ResponseEntity.status(HttpStatus.OK).body(
                 new StandardResponse<>(
                         200,
                         "Details fetched",
                         LocalDateTime.now(),
                         responseDTOS)
        );
    }

    //register vehicle
    @PostMapping("/register")
    public ResponseEntity<StandardResponse<VehicleResponseDTO>> saveVehicle(
            @RequestBody VehicleRequestDTO vehicleRequestDTO
            ){
        //requestDTO to domain model
        Vehicle vehicleDomainModel = vehicleWebMapper.toDomainModel(vehicleRequestDTO);

        //get domain model and turn into responseDTO
        VehicleUpdateResult registerResult = vehicleUseCase.saveVehicle(vehicleDomainModel);

        //use mapper for create response dto
        VehicleResponseDTO responseDTO = vehicleWebMapper.customResponseDTO(registerResult);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new StandardResponse<>(
                        201,
                        "Vehicle registered successful",
                        LocalDateTime.now(),
                        responseDTO)
        );
    }


    //update vehicle details
    @PutMapping("/{vehicleId}")
    public ResponseEntity<StandardResponse<VehicleResponseDTO>> updateVehicle(
            @PathVariable Long vehicleId,
            @RequestBody VehicleRequestDTO vehicleRequestDTO
    ){
        //turn to domain model
        Vehicle vehicleDomainModel = vehicleWebMapper.toDomainModel(vehicleRequestDTO);

        //set id and domain model to usecase
        VehicleUpdateResult result = vehicleUseCase.updateVehicle(vehicleId, vehicleDomainModel);

        //use mapper create responseDTO
        VehicleResponseDTO toResponse = vehicleWebMapper.customResponseDTO(result);

        //return response with updated values
        return ResponseEntity.status(HttpStatus.OK).body(
                new StandardResponse<>(
                        200,
                        "Vehicle updated successful",
                        LocalDateTime.now(),
                        toResponse
                )
        );
    }

    //delete vehicle
    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<String> deleteVehicle(
            @PathVariable Long vehicleId
    ){
        //directly set id to usecase
        vehicleUseCase.deleteVehicle(vehicleId);

       return ResponseEntity.status(HttpStatus.OK).body("Vehicle deleted successful");
    }
}
