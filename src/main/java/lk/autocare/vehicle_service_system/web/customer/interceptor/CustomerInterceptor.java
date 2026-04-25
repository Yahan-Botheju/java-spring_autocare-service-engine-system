package lk.autocare.vehicle_service_system.web.customer.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class CustomerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull Object handler
    ) throws Exception {

        String uri = request.getRequestURI();
        String requestMethod = request.getMethod();
        String role = request.getHeader("role");

        //only admin can view all the vehicles
        if("GET".equalsIgnoreCase(requestMethod) && uri.startsWith("/api/v1/autocare/customers/all")) {
             checkAdminRole(role);
        }

        //only admin can delete customer
        if("DELETE".equalsIgnoreCase(requestMethod) && uri.startsWith("/api/v1/autocare/customers/")) {
           checkAdminRole(role);
        }

        return true;
    }

    //create common ADMIN check method
    private void checkAdminRole(String role){
        if(role == null || !role.trim().equalsIgnoreCase("ADMIN")){
            throw new org.springframework.security.access.AccessDeniedException("Access Denied: ADMIN authorization required..!!");
        }
    }
}
