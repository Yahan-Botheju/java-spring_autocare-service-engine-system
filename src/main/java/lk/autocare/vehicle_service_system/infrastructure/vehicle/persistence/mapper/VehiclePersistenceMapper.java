package lk.autocare.vehicle_service_system.infrastructure.vehicle.persistence.mapper;

import lk.autocare.vehicle_service_system.domain.models.Vehicle;
import lk.autocare.vehicle_service_system.infrastructure.vehicle.persistence.entity.VehicleEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface VehiclePersistenceMapper {

    //domain model to entity
    @Mapping(source = "customerId", target = "customer.customerId")
    VehicleEntity toEntity(Vehicle vehicle);

    //entity to domain model\
    @Mapping(source = "customer.customerId", target = "customerId")
    Vehicle toDomainModel(VehicleEntity vehicleEntity);

    //old entity to new domain model edited data
    VehicleEntity oldEntityDataToNewEntity(Vehicle vehicle ,@MappingTarget  VehicleEntity vehicleEntity);

}
