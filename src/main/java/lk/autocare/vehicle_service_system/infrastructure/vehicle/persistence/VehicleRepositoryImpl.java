package lk.autocare.vehicle_service_system.infrastructure.vehicle.persistence;

import lk.autocare.vehicle_service_system.GlobalExceptionHandler.ResourceNotFoundException;
import lk.autocare.vehicle_service_system.domain.models.Vehicle;
import lk.autocare.vehicle_service_system.domain.repositories.VehicleRepository;
import lk.autocare.vehicle_service_system.infrastructure.vehicle.persistence.entity.VehicleEntity;
import lk.autocare.vehicle_service_system.infrastructure.vehicle.persistence.jpa.JpaVehicleRepository;
import lk.autocare.vehicle_service_system.infrastructure.vehicle.persistence.mapper.VehiclePersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class VehicleRepositoryImpl implements  VehicleRepository {

    //inject domain repo
    private final JpaVehicleRepository jpaVehicleRepository;

    //inject persistence mapper
    private final VehiclePersistenceMapper  vehiclePersistenceMapper;

    //vehicle find by id
    @Override
    public Optional<Vehicle> findById(Long vehicleId){
        return jpaVehicleRepository.findById(vehicleId)
                .map(vehicle -> vehiclePersistenceMapper.toDomainModel(vehicle));
    }

    //get all vehicle
    @Override
    public List<Vehicle> getAllVehicles(int page, int size){
        //get all vehicle
        Page<VehicleEntity> vehicleEntityPage  = jpaVehicleRepository.findAll(PageRequest.of(page, size));

        //find all vehicles from db then attached to list and return
        return vehicleEntityPage.stream()
                .map(vehicle -> vehiclePersistenceMapper.toDomainModel(vehicle)).toList();
    }

    //create vehicle
    @Override
    public Vehicle saveVehicle(Vehicle vehicle){
        //turn domain model to entity
        VehicleEntity vehicleEntity = vehiclePersistenceMapper.toEntity(vehicle);
        //save in db
        jpaVehicleRepository.save(vehicleEntity);
        //return vehicle turning to domain model to mapper for response
        return  vehiclePersistenceMapper.toDomainModel(vehicleEntity);
    }

    //update vehicle details
    @Override
    public Vehicle updateVehicle(Long vehicleId, Vehicle vehicle){
        //check vehicle exists
        VehicleEntity vehicleEntity = jpaVehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found" + " "  + vehicleId));

        //changed fields update with existing fields in entity using persistence mapper
        VehicleEntity updatedEntity = vehiclePersistenceMapper.oldEntityDataToNewEntity(vehicle, vehicleEntity);

        //save in db
        jpaVehicleRepository.save(updatedEntity);

        //updated entity turn into domain model and return for response
        return vehiclePersistenceMapper.toDomainModel(updatedEntity);
    }

    //delete vehicle
    @Override
    public void deleteVehicle(Long vehicleId){
        //check availability of vehicle
        jpaVehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found" + " "  + vehicleId));
        //set to remove from db through domain repo
        jpaVehicleRepository.deleteById(vehicleId);

    }
}
