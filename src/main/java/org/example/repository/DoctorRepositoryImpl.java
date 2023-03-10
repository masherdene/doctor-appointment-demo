package org.example.repository;

import org.example.model.Doctor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoctorRepositoryImpl implements DoctorRepository {

    private final Map<String, Doctor> doctorDb = new HashMap<>();

    public DoctorRepositoryImpl() {
        this.doctorDb.put("1001",new Doctor("1001","Alfred Williams","orthodontist"));
        this.doctorDb.put("1002",new Doctor("1002","Daniel Lewis","periodontist"));
        this.doctorDb.put("1003",new Doctor("1003","Sarah Johnson","endodontist"));
        this.doctorDb.put("1004",new Doctor("1004","Alice Griffith","prosthodontist"));
    }

    @Override
    public Doctor findDoctorById(String doctorId){
        return this.doctorDb.get(doctorId);
    }
    @Override
    public Doctor findDoctorByName(String doctorName){
        return this.doctorDb.get(doctorName);
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
