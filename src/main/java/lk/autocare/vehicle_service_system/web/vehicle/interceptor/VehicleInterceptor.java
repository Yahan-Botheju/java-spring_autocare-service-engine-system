package lk.autocare.vehicle_service_system.web.vehicle.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
public class VehicleInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull Object handler
            )throws Exception{

        String uri = request.getRequestURI();
        String requestMethod = request.getMethod();
        String role = request.getHeader("role");

        //only admin can delete vehicles
        if("DELETE".equalsIgnoreCase(requestMethod) && uri.startsWith("/api/v1/autocare/vehicles/")) {
            checkIsAdmin(role);
        }

        //only admin can register vehicle
        if("POST".equalsIgnoreCase(requestMethod) && uri.startsWith("/api/v1/autocare/vehicles/register")) {
            checkIsAdmin(role);
        }

        //only admin can get all vehicles details
        if("GET".equalsIgnoreCase(requestMethod) && uri.startsWith("/api/v1/autocare/vehicles/all")){
            checkIsAdmin(role);
        }

        //only admin can update vehicles details
        if("PUT".equalsIgnoreCase(requestMethod) && uri.startsWith("/api/v1/autocare/vehicles/")){
            checkIsAdmin(role);
        }

        return true;
    }

    //create common ADMIN check method
    private void checkIsAdmin(String role){
        if(role == null || !role.trim().equalsIgnoreCase("ADMIN")){
            throw new org.springframework.security.access.AccessDeniedException("Access Denied: ADMIN authorization required..!!");
        }
    }
}
