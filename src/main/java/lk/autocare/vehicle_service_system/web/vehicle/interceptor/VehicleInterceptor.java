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
            if(role == null || !role.trim().equalsIgnoreCase("ADMIN")){
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("Access Denied :  Only ADMIN can delete this vehicle");

                return false;
            }
        }

        //only admin can register vehicle
        if("POST".equalsIgnoreCase(requestMethod) && uri.startsWith("/api/v1/autocare/vehicles/register")) {
            if(role == null || !role.trim().equalsIgnoreCase("ADMIN")){
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("Access Denied :  Only ADMIN can create this vehicle");

                return false;
            }
        }

        //only admin can get all vehicles details
        if("GET".equalsIgnoreCase(requestMethod) && uri.startsWith("/api/v1/autocare/vehicles/all")){
            if(role == null || !role.trim().equalsIgnoreCase("ADMIN")){
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("Access Denied :  Only ADMIN can get all vehicles details");

                return false;
            }
        }


        return true;

    }

}
