package lk.autocare.vehicle_service_system.web.config;

import lk.autocare.vehicle_service_system.web.customer.interceptor.CustomerInterceptor;
import lk.autocare.vehicle_service_system.web.vehicle.interceptor.VehicleInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    //inject customer role base interceptor
    private final CustomerInterceptor customerInterceptor;

    //inject vehicle role base interceptor
    private final VehicleInterceptor vehicleInterceptor;


}
