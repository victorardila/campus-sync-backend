package com.facade.pattern.campus_sync.repositories.database;

import org.springframework.data.jpa.repository.JpaRepository;

import com.facade.pattern.campus_sync.domains.Scholarship;

public interface JpaScholarshipRepository extends JpaRepository<Scholarship, String> {
    // This is a JPA repository interface for Scholarship entity
}
