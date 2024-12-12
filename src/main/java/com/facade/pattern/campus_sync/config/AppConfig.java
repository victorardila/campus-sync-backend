package com.facade.pattern.campus_sync.config;

import com.facade.pattern.campus_sync.repositories.StudentRepository;
import com.facade.pattern.campus_sync.repositories.memory.InMemoryStudentRepository;
import com.facade.pattern.campus_sync.repositories.database.JpaStudentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class AppConfig {

    @Bean
    @Profile("memory")
    public StudentRepository inMemoryStudentRepository() {
        return new InMemoryStudentRepository();
    }

    @Bean
    @Profile("database")
    public StudentRepository jpaStudentRepository(JpaStudentRepository jpaStudentRepository) {
        return jpaStudentRepository;
    }
}
