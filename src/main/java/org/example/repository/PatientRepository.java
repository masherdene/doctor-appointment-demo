package org.example.repository;

import org.example.model.Patient;
import java.util.List;

// For now repository works with domain model. Later repository should work with domain entities.
public interface PatientRepository {

    Patient findPatientById(String patientId);
    List<Patient> findAllPatients();
    void deletePatientById(String patientId);
    void addPatient(Patient patient);
}
