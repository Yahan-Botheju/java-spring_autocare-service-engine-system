package lk.autocare.vehicle_service_system.web.customer.webMappers;


import lk.autocare.vehicle_service_system.domain.models.Customer;
import lk.autocare.vehicle_service_system.web.customer.DTOs.CustomerRequestDTO;
import lk.autocare.vehicle_service_system.web.customer.DTOs.CustomerResponseDTO;
import lk.autocare.vehicle_service_system.web.vehicle.webMappers.VehicleWebMapper;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = {VehicleWebMapper.class},
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface CustomerWebMapper {

    //RequestDTO to domain model
    Customer toDomainModel(CustomerRequestDTO customerRequestDTO);

    //domain model to ResponseDTO
    CustomerResponseDTO toResponseDTO(Customer customer);
}
