package com.facade.pattern.campus_sync;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    @SuppressWarnings("null")
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**") // Aplica a todas las rutas de tu API
                .allowedOrigins("http://localhost:3000") // Cambia el puerto o dominio si es necesario
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos permitidos
                .allowedHeaders("*") // Permitir todos los headers
                .allowCredentials(true); // Si necesitas enviar cookies o credenciales
    }
}
