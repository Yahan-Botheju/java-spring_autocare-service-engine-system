package lk.autocare.vehicle_service_system.infrastructure.vehicle.persistence.mapper;

import lk.autocare.vehicle_service_system.domain.models.Vehicle;
import lk.autocare.vehicle_service_system.infrastructure.vehicle.persistence.entity.VehicleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface VehiclePersistenceMapper {

    //domain model to entity
    VehicleEntity toEntity(Vehicle vehicle);

    //entity to domain model
    Vehicle toDomainModel(VehicleEntity vehicleEntity);

    //old entity to new domain model edited data
    VehicleEntity oldEntityDataToNewEntity(Vehicle vehicle ,@MappingTarget  VehicleEntity vehicleEntity);

}
