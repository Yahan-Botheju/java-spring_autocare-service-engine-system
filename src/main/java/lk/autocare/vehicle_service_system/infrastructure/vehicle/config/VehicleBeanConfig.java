package lk.autocare.vehicle_service_system.infrastructure.vehicle.config;

import lk.autocare.vehicle_service_system.domain.repositories.VehicleRepository;
import lk.autocare.vehicle_service_system.usecase.vehicle.VehicleUseCase;
import lk.autocare.vehicle_service_system.usecase.vehicle.VehicleUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VehicleBeanConfig {
    @Bean
    public VehicleUseCase vehicleUseCase(VehicleRepository vehicleRepository) {
        return new VehicleUseCaseImpl(vehicleRepository);
    }
}
