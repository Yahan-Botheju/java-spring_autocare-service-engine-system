package lk.autocare.vehicle_service_system.usecase.vehicle;

import lk.autocare.vehicle_service_system.domain.repositories.VehicleRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class VehicleUseCaseImpl implements  VehicleUseCase{

    //inject domain repo as bean this class
    private final VehicleRepository vehicleRepository;
}
