package org.example.rest;

import lombok.Getter;
import lombok.Setter;
import org.example.model.Doctor;
import org.example.model.Patient;
import org.example.model.Treatment;
import org.example.repository.DoctorRepository;
import org.example.repository.PatientRepository;
import org.example.repository.TreatmentRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class RestAppointmentDetailsMapper {

    private String patientName;
    private String patientCondition;
    private String patientId;
    private String doctorName;
    private String doctorSpecialization;
    private String doctorId;
    private List<String> treatmentNames;
    private List<String> treatmentIds;
    private LocalDateTime dateTime;

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final TreatmentRepository treatmentRepository;

    public RestAppointmentDetailsMapper(String patientName, String doctorName, List<String> treatmentNames, LocalDateTime dateTime, PatientRepository patientRepository, DoctorRepository doctorRepository, TreatmentRepository treatmentRepository) {
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.treatmentNames = treatmentNames;
        this.dateTime = dateTime;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.treatmentRepository = treatmentRepository;
    }

    public void modelToRestAppointmentDetails(String patientName){
        if(patientRepository.findPatientByName(patientName)==null){
            String patientId = UUID.randomUUID().toString();
            Patient patient = new Patient(patientId,this.getPatientName(),this.getPatientCondition());
            patientRepository.addPatient(patient);
            this.setPatientId(patientId);
        } else {
            Patient patient = patientRepository.findPatientByName(patientName);
            this.setPatientId(patient.getPatientId());

            Doctor doctor = doctorRepository.findDoctorByName(doctorName);
            this.setDoctorId(doctor.getDoctorId());

            for (String name : treatmentNames) {
                Treatment treatment = treatmentRepository.findTreatmentByName(name);
                this.treatmentIds.add(treatment.getTreatmentId());
            }
        }
    }
}
