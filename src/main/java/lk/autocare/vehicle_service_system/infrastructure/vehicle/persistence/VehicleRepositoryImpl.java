package lk.autocare.vehicle_service_system.infrastructure.vehicle.persistence;

import lk.autocare.vehicle_service_system.domain.repositories.VehicleRepository;
import lk.autocare.vehicle_service_system.infrastructure.vehicle.persistence.jpa.JpaVehicleRepository;
import lk.autocare.vehicle_service_system.infrastructure.vehicle.persistence.mapper.VehiclePersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class VehicleRepositoryImpl implements  VehicleRepository {

    //inject domain repo
    private final JpaVehicleRepository jpaVehicleRepository;

    //inject persistence mapper
    private final VehiclePersistenceMapper  vehiclePersistenceMapper;
}
