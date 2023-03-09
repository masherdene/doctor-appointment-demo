package org.example.repository;

import org.example.model.Doctor;
import java.util.List;

// For now repository works with domain model. Later repository should work with domain entities.
public interface DoctorRepository {

    Doctor findDoctorById(String doctorId);
    Doctor findDoctorByName(String doctorName);
    List<Doctor> findAllDoctors();
    void deleteDoctorById(String doctorId);
    void addDoctor(Doctor doctor);

}
