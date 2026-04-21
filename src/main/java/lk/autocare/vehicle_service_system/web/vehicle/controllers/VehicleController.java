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
                 .map(vehicleUpdateResult -> {

                     //turn into response dto and attached customer id and name then return
                     VehicleResponseDTO responseDTO = vehicleWebMapper.toResponseDTO(vehicleUpdateResult.getVehicle());
                                        responseDTO.setCustomerId(vehicleUpdateResult.getCustomer().getCustomerId());
                                        responseDTO.setCustomerName(vehicleUpdateResult.getCustomer().getCustomerName());
                                        return responseDTO;
                 }).toList();

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

        VehicleResponseDTO responseDTO = vehicleWebMapper.toResponseDTO(registerResult.getVehicle());
                           responseDTO.setCustomerId(registerResult.getCustomer().getCustomerId());
                           responseDTO.setCustomerName(registerResult.getCustomer().getCustomerName());

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

        //make response dto using customer model which come from usecase
        VehicleResponseDTO toResponse = vehicleWebMapper.toResponseDTO(result.getVehicle());
                           toResponse.setCustomerId(result.getCustomer().getCustomerId());
                           toResponse.setCustomerName(result.getCustomer().getCustomerName());


        //return response with updated values
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new StandardResponse<>(
                        201,
                        "Vehicle updated successful",
                        LocalDateTime.now(),
                        toResponse
                )
        );
    }

    //delete vehicle
    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<StandardResponse<VehicleResponseDTO>> deleteVehicles(
            @PathVariable Long vehicleId
    ){
        //directly set id to usecase
        VehicleUpdateResult deleteResult = vehicleUseCase.deleteVehicle(vehicleId);

        //get domain models and turn them into response
        VehicleResponseDTO toResponse = vehicleWebMapper.toResponseDTO(deleteResult.getVehicle());
                           toResponse.setCustomerId(deleteResult.getCustomer().getCustomerId());
                           toResponse.setCustomerName(deleteResult.getCustomer().getCustomerName());


       return ResponseEntity.status(HttpStatus.OK).body(
                new StandardResponse<>(
                        200,
                        "Vehicle deleted successful",
                        LocalDateTime.now(),
                        toResponse
                )
       );
    }
}
