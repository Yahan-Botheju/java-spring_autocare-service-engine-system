package lk.autocare.vehicle_service_system.web.customer.webMappers;


import lk.autocare.vehicle_service_system.domain.models.Customer;
import lk.autocare.vehicle_service_system.web.customer.DTOs.CustomerRequestDTO;
import lk.autocare.vehicle_service_system.web.customer.DTOs.CustomerResponseDTO;
import lk.autocare.vehicle_service_system.web.vehicle.webMappers.VehicleWebMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {VehicleWebMapper.class})
public interface CustomerWebMapper {

    //RequestDTO to domain model
    @Mapping(target = "customerId", ignore = true)
    @Mapping(target = "vehicles", ignore = true)
    Customer toDomainModel(CustomerRequestDTO customerRequestDTO);

    //domain model to ResponseDTO
    CustomerResponseDTO toResponseDTO(Customer customer);
}
