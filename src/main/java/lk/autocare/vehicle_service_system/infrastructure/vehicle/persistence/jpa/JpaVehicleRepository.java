package lk.autocare.vehicle_service_system.infrastructure.vehicle.persistence.jpa;

import lk.autocare.vehicle_service_system.infrastructure.vehicle.persistence.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaVehicleRepository extends JpaRepository<VehicleEntity,Long> {
}
