package lk.autocare.vehicle_service_system.web.vehicle.webMappers;

import lk.autocare.vehicle_service_system.domain.models.Vehicle;
import lk.autocare.vehicle_service_system.web.vehicle.DTOs.VehicleShortInfoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VehicleWebMapper {

    //vehicle model to vehicleShortIntoDTO
    VehicleShortInfoDTO toVehicleShortInfoDTO(Vehicle vehicle);
}
