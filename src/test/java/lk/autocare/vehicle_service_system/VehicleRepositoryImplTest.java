package lk.autocare.vehicle_service_system;

import lk.autocare.vehicle_service_system.GlobalExceptionHandler.ResourceNotFoundException;
import lk.autocare.vehicle_service_system.domain.models.Vehicle;
import lk.autocare.vehicle_service_system.infrastructure.vehicle.persistence.VehicleRepositoryImpl;
import lk.autocare.vehicle_service_system.infrastructure.vehicle.persistence.entity.VehicleEntity;
import lk.autocare.vehicle_service_system.infrastructure.vehicle.persistence.jpa.JpaVehicleRepository;
import lk.autocare.vehicle_service_system.infrastructure.vehicle.persistence.mapper.VehiclePersistenceMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VehicleRepositoryImplTest {

    @Mock
    private JpaVehicleRepository jpaVehicleRepository;

    @Mock
    private VehiclePersistenceMapper vehiclePersistenceMapper;

    @InjectMocks
    private VehicleRepositoryImpl vehicleRepositoryImpl;

    @Test
    void shouldFindById(){
        Long vehicleId = 1L;
        VehicleEntity dummyVehicleEntity = new VehicleEntity();
        Vehicle dummyVehicleDomain = new Vehicle();

        when(jpaVehicleRepository.findById(vehicleId)).thenReturn(Optional.of(dummyVehicleEntity));

        when(vehiclePersistenceMapper.toDomainModel(dummyVehicleEntity)).thenReturn(dummyVehicleDomain);

        Optional<Vehicle> result = vehicleRepositoryImpl.findById(vehicleId);

        assertTrue(result.isPresent());
        assertEquals(dummyVehicleDomain, result.get());

    }

    @Test
    void shouldDeleteVehicle(){
        Long vehicleId = 1L;
        VehicleEntity dummyVehicleEntity = new VehicleEntity();

        when(jpaVehicleRepository.findById(vehicleId)).thenReturn(Optional.of(dummyVehicleEntity));

        vehicleRepositoryImpl.deleteVehicle(vehicleId);

        verify(jpaVehicleRepository,times(1)).deleteById(vehicleId);
    }

    @Test
    void shouldSaveVehicle(){
        VehicleEntity dummyVehicleEntity = new VehicleEntity();
        Vehicle dummyVehicleDomain = new Vehicle();

        //request entity by giving domain model
        when(vehiclePersistenceMapper.toEntity(dummyVehicleDomain))
                .thenReturn(dummyVehicleEntity);

        //request domain model by giving entity
        when(vehiclePersistenceMapper.toDomainModel(dummyVehicleEntity))
                .thenReturn(dummyVehicleDomain);

        //tell jpa to save the entity and return entity
        when(jpaVehicleRepository.save(dummyVehicleEntity))
                .thenReturn(dummyVehicleEntity);

        //call repoImpl method and save vehicle
        Vehicle result = vehicleRepositoryImpl.saveVehicle(dummyVehicleDomain);

        //check create domain model is saved in db
        assertEquals(dummyVehicleDomain, result);

        //check new record has created
        verify(jpaVehicleRepository,times(1)).save(dummyVehicleEntity);
    }

    @Test
    void shouldThrowResourceNotFoundException(){
        Long vehicleId = 1L;
        VehicleEntity dummyVehicleEntity = new VehicleEntity();
        Vehicle dummyVehicleDomain = new Vehicle();

        when(jpaVehicleRepository.findById(vehicleId)).thenReturn(Optional.of(dummyVehicleEntity));

        assertThrows(ResourceNotFoundException.class,()->{
            vehicleRepositoryImpl.updateVehicle(vehicleId, dummyVehicleDomain);
        });
    }

    @Test
    void shouldUpdateVehicle(){
        Long vehicleId = 1L;
        Vehicle newDomainData = new Vehicle();
        Vehicle updatedDomain = new Vehicle();
        VehicleEntity existingEntity = new VehicleEntity();
        VehicleEntity updatedEntity = new VehicleEntity();

        when(jpaVehicleRepository.findById(vehicleId))
                .thenReturn(Optional.of(existingEntity));

        when(vehiclePersistenceMapper.oldEntityDataToNewEntity(newDomainData, existingEntity))
                .thenReturn(updatedEntity);

        when(jpaVehicleRepository.save(updatedEntity))
                .thenReturn(updatedEntity);

        when(vehiclePersistenceMapper.toDomainModel(updatedEntity))
                .thenReturn(updatedDomain);
        Vehicle result = vehicleRepositoryImpl.updateVehicle(vehicleId, newDomainData);

        assertEquals(updatedDomain, result);
        verify(jpaVehicleRepository,times(1)).save(updatedEntity);
    }
}


