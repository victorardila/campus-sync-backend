package com.facade.pattern.campus_sync.repositories.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.facade.pattern.campus_sync.domains.Scholarship;
import com.facade.pattern.campus_sync.repositories.ScholarshipRepository;

@Repository
public interface JpaScholarshipRepository extends JpaRepository<Scholarship, String>, ScholarshipRepository {
    // No es necesario implementar los métodos, ya que JpaRepository proporciona las
    // implementaciones
}
