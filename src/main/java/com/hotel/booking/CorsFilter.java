package com.hotel.booking;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;
import org.springframework.stereotype.Component;


@Component
@Provider
public class CorsFilter implements ContainerResponseFilter {
    /*
    CorsFilter class allows FE app and BE app using different domain.
    Without adding "Access-Control-Allow-Origin" to Headers
    FE keep getting CORS (Cross-Origin Resource Sharing) issues
    due to the browser's security policy that prevents cross-origin
    requests.
     */
    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
        responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
        responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        responseContext.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
    }
}
