package lk.autocare.vehicle_service_system.infrastructure.customer.persistence.jpa;

import lk.autocare.vehicle_service_system.infrastructure.customer.persistence.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCustomerRepository extends JpaRepository<CustomerEntity, Long> {
}
