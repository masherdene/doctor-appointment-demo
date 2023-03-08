package org.example.repository;

import org.example.model.Doctor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoctorRepositoryImpl implements DoctorRepository {

    private final Map<String, Doctor> doctorDb = new HashMap<>();

    @Override
    public Doctor findDoctorById(String doctorId){
        return this.doctorDb.get(doctorId);
    }

    @Override
    public List<Doctor> findAllDoctors(){
        return new ArrayList<>(this.doctorDb.values());
    }

    @Override
    public void addDoctor(Doctor doctor){
        this.doctorDb.put(doctor.getDoctorId(),doctor);
    }

    @Override
    public void deleteDoctorById(String doctorId){
        this.doctorDb.remove(doctorId);
    }

}
