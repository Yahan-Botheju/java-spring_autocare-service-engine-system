package lk.autocare.vehicle_service_system.infrastructure.vehicle.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lk.autocare.vehicle_service_system.domain.models.VehicleFuelType;
import lk.autocare.vehicle_service_system.infrastructure.customer.persistence.entity.CustomerEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SoftDelete;

import java.time.LocalDate;

@Entity
@Table(name = "vehicles")
@SoftDelete(columnName = "is_deleted")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicleId;
    @NotBlank(message = "Vehicle number cannot be empty")
    private String vehicleNumber;
    @NotBlank(message = "Vehicle model cannot be empty")
    private String vehicleModel;
    @NotNull(message = "Last service date cannot be empty")
    private LocalDate lastServiceDate;
    @Min(value = 10, message = "At least vehicle should be 10 miles ")
    private int mileage;

    @Enumerated(EnumType.STRING)
    private VehicleFuelType vehicleFuelType;

    @ManyToOne
    @JoinColumn(name = "customerId", nullable = false)
    private CustomerEntity customer ;

}
