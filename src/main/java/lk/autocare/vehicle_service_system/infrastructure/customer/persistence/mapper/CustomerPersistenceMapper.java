package lk.autocare.vehicle_service_system.infrastructure.customer.persistence.mapper;

import lk.autocare.vehicle_service_system.domain.models.Customer;
import lk.autocare.vehicle_service_system.infrastructure.customer.persistence.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerPersistenceMapper {

    //entity to domain model
    Customer toDomainModel(CustomerEntity customerEntity);

    //domain model to entity
    CustomerEntity toEntity(Customer customer);

    //copy new domain model data to old entity(just edited fields)
    CustomerEntity updateEntityWithNewDomainModel(Customer customer, @MappingTarget CustomerEntity customerEntity);
}
