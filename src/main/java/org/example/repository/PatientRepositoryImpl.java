package org.example.repository;

import org.example.model.Patient;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Repository
public class PatientRepositoryImpl implements PatientRepository {

    private final Map<String, Patient> patientDb = new HashMap<>();

    @Override
    public Patient findPatientById(String patientId){
        return this.patientDb.get(patientId);
    }
    @Override
    public Patient findPatientByName(String patientName){
        return this.patientDb.get(patientName);
    }

    @Override
    public List<Patient> findAllPatients(){
        return new ArrayList<>(this.patientDb.values());
    }

    @Override
    public void addPatient(Patient patient){
        this.patientDb.put(patient.getPatientId(),patient);
    }

    @Override
    public void deletePatientById(String patientId){
        this.patientDb.remove(patientId);
    }

}
