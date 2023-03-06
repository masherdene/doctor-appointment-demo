package org.example.infrastructure.repository;

import org.example.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

// For now repository works with domain model. Later repository should work with domain entities.
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
}
