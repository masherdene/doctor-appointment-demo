package org.example.repository;

import org.example.model.Treatment;
import java.util.List;

// For now repository works with domain model. Later repository should work with domain entities.
public interface TreatmentRepository {

    Treatment findTreatmentById(String treatmentId);
    List<Treatment> findAllTreatments();
    List<Treatment> findTreatmentsByIds(List<String> treatmentIds);
    void deleteTreatmentById(String treatmentId);
    void addTreatment(Treatment treatment);
}
