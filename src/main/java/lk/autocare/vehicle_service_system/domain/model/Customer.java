package lk.autocare.vehicle_service_system.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private Long customerId;
    private String customerName;
    private String customerPhone;
    private String customerEmail;
    private List<Vehicle> vehicles;
}
