package com.facade.pattern.campus_sync.config;

import com.facade.pattern.campus_sync.repositories.database.JpaStudentRepository;
import com.facade.pattern.campus_sync.repositories.memory.InMemoryStudentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class AppConfig {

    // Configuración para usar el repositorio en memoria
    @Bean
    @Profile("memory")
    public InMemoryStudentRepository inMemoryStudentRepository() {
        return new InMemoryStudentRepository();
    }

    // No es necesario definir JpaStudentRepository aquí, ya que Spring Data JPA lo
    // hace automáticamente
}
