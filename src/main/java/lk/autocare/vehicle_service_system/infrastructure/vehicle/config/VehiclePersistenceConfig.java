package lk.autocare.vehicle_service_system.infrastructure.vehicle.config;

import lk.autocare.vehicle_service_system.domain.repositories.VehicleRepository;
import lk.autocare.vehicle_service_system.infrastructure.vehicle.persistence.VehicleRepositoryImpl;
import lk.autocare.vehicle_service_system.infrastructure.vehicle.persistence.jpa.JpaVehicleRepository;
import lk.autocare.vehicle_service_system.infrastructure.vehicle.persistence.mapper.VehiclePersistenceMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VehiclePersistenceConfig {
    @Bean
    public VehicleRepository vehicleRepository(
            JpaVehicleRepository    jpaVehicleRepository,
            VehiclePersistenceMapper vehiclePersistenceMapper
    ) {
        return new VehicleRepositoryImpl(jpaVehicleRepository,  vehiclePersistenceMapper);
    }
}
