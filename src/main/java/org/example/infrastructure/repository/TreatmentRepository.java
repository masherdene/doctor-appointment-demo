package org.example.infrastructure.repository;

import org.example.model.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.ArrayList;
import java.util.List;

// For now repository works with domain model. Later repository should work with domain entities.
public interface TreatmentRepository extends JpaRepository<Treatment, Integer> {
    List<Treatment> findAllByTreatmentId(int treatmentId);
}
