package lk.autocare.vehicle_service_system.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleUpdateResult {
    private Vehicle vehicle;
    private Customer customer;
}
