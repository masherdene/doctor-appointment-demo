package org.example.infrastructure.repository;

import org.example.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

// For now repository works with domain model. Later repository should work with domain entities.
public interface PatientRepository extends JpaRepository<Patient, Integer> {
}
