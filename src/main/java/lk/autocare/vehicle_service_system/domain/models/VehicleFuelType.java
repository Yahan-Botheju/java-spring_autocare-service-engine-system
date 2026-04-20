package lk.autocare.vehicle_service_system.domain.models;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum VehicleFuelType {
    PETROL,
    DIESEL,
    HYBRID,
    ELECTRIC;

    //turn lowercase to uppercase
    @JsonCreator
    public static VehicleFuelType toCapitalLetter(String value) {
        return VehicleFuelType.valueOf(value.toUpperCase());
    }
}
