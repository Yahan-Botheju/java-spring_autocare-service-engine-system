package lk.autocare.vehicle_service_system;

import lk.autocare.vehicle_service_system.GlobalExceptionHandler.ServiceAlreadyCompletedException;
import lk.autocare.vehicle_service_system.domain.models.Vehicle;
import lk.autocare.vehicle_service_system.domain.models.VehicleServiceStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


public class VehicleDomainTest {

    @Test
    public void shouldThrowExceptionWhenServiceIsCompleted(){

        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleServiceStatus(VehicleServiceStatus.COMPLETED);

        assertThrows(ServiceAlreadyCompletedException.class, vehicle::disableVehicleUpdate);

    }

    @Test
    public void shouldNotThrowExceptionWhenVehicleIsDisabled(){

        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleServiceStatus(VehicleServiceStatus.PENDING);

        assertDoesNotThrow(vehicle::disableVehicleUpdate);

    }
    @Test
    public void setDefaultVehicleServiceStatus(){
        Vehicle vehicle = new Vehicle();

        vehicle.setDefaultVehicleServiceStatus();


        assertEquals(VehicleServiceStatus.PENDING, vehicle.getVehicleServiceStatus());
    }

    //negative case
    @Test
    public void setDefaultVehicleStatusNegative(){
        Vehicle vehicle = new Vehicle();

        vehicle.setVehicleServiceStatus(VehicleServiceStatus.COMPLETED);

        vehicle.setDefaultVehicleServiceStatus();

        assertEquals(VehicleServiceStatus.COMPLETED, vehicle.getVehicleServiceStatus());
    }

    @Test
    public void shouldCalculateNextServiceDateCorrectly(){

        Vehicle vehicle = new Vehicle();
        vehicle.setLastServiceDate(LocalDate.of(2020,1,1));
        vehicle.updateNextServiceDate();

        assertEquals(LocalDate.of(2020,7,1), vehicle.getNextServiceDate());
    }
}
