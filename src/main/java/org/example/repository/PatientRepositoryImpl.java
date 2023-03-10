package org.example.repository;

import org.example.model.Patient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatientRepositoryImpl implements PatientRepository {

    private final Map<String, Patient> patientDb = new HashMap<>();

    public PatientRepositoryImpl() {
        this.patientDb.put("80001",new Patient("80001","Richard Miller","periodontitis"));
        this.patientDb.put("80002",new Patient("80002","Johanna Jones","orthodontitis"));
        this.patientDb.put("80003",new Patient("80003","Michelle Meyer","endodontitis"));
        this.patientDb.put("80004",new Patient("80004","John Robson","orthodontitis"));
        this.patientDb.put("80005",new Patient("80005","Luke Brown","prosthodontitis"));
    }

    @Override
    public Patient findPatientById(String patientId){
        return this.patientDb.get(patientId);
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
