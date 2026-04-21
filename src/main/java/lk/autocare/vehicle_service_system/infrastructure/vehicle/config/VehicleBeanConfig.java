package lk.autocare.vehicle_service_system.infrastructure.vehicle.config;

import lk.autocare.vehicle_service_system.domain.repositories.CustomerRepository;
import lk.autocare.vehicle_service_system.domain.repositories.VehicleRepository;
import lk.autocare.vehicle_service_system.usecase.vehicle.VehicleUseCase;
import lk.autocare.vehicle_service_system.usecase.vehicle.VehicleUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class VehicleBeanConfig {
    @Bean
    VehicleUseCase vehicleUseCase(
            VehicleRepository vehicleRepository,
            CustomerRepository customerRepository) {
        return new VehicleUseCaseImpl(vehicleRepository,  customerRepository);
    }
}
