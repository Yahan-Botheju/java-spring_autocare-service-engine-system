package lk.autocare.vehicle_service_system.infrastructure.customer.persistence.mapper;

import lk.autocare.vehicle_service_system.domain.models.Customer;
import lk.autocare.vehicle_service_system.infrastructure.customer.persistence.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerPersistenceMapper {

    //entity to domain model
    Customer toDomainModel(CustomerEntity customerEntity);

    //domain model to entity
    @Mapping(target = "customerId", source = "customerId")
    CustomerEntity toEntity(Customer customer);
}
