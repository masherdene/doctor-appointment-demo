package org.example.infrastructure.mapper;

import org.example.infrastructure.entity.AppointmentEntity;
import org.example.infrastructure.entity.DoctorEntity;
import org.example.infrastructure.entity.PatientEntity;
import org.example.infrastructure.entity.TreatmentEntity;
import org.example.model.Appointment;
import org.example.model.Doctor;
import org.example.model.Patient;
import org.example.model.Treatment;

public class ModelEntityMapperImpl implements ModelEntityMapper {

    @Override
    public AppointmentEntity appointmentToAppointmentEntity(Appointment appointment){
        if (appointment == null) {
            return null;
        }
        AppointmentEntity appointmentEntity = new AppointmentEntity();
        appointmentEntity.setAppointmentId(appointment.getAppointmentId());
        appointmentEntity.setAppointmentDate(appointment.getAppointmentDate());
        appointmentEntity.setDoctor(new DoctorEntity());
        appointmentEntity.setPatient(new PatientEntity());
        appointmentEntity.setTreatment(new TreatmentEntity());
        return appointmentEntity;
    }

    @Override
    public Appointment appointmentEntityToAppointment(AppointmentEntity appointmentEntity) {
        if (appointmentEntity == null) {
            return null;
        }
        Appointment appointment = new Appointment();
        appointment.setAppointmentId(appointmentEntity.getAppointmentId());
        appointment.setAppointmentDate(appointmentEntity.getAppointmentDate());
        appointment.setDoctorId(appointmentEntity.getDoctor().getDoctorId());
        appointment.setPatientId(appointmentEntity.getPatient().getPatientId());
        appointment.setTreatmentId(appointmentEntity.getTreatment().getTreatmentId());
        return appointment;
    }

    @Override
    public DoctorEntity doctorToDoctorEntity(Doctor doctor){
        if (doctor == null){
            return null;
        }
        DoctorEntity doctorEntity = new DoctorEntity();
        doctorEntity.setDoctorId(doctor.getDoctorId());
        doctorEntity.setDoctorName(doctor.getDoctorName());
        doctorEntity.setDoctorSpecialization(doctor.getDoctorSpecialization());
        return doctorEntity;
    }

    @Override
    public Doctor doctorEntityToDoctor(DoctorEntity doctorEntity) {
        if (doctorEntity == null) {
            return null;
        }
        Doctor doctor = new Doctor();
        doctor.setDoctorId(doctorEntity.getDoctorId());
        doctor.setDoctorName(doctorEntity.getDoctorName());
        doctor.setDoctorSpecialization(doctorEntity.getDoctorSpecialization());
        return doctor;
    }

    @Override
    public PatientEntity patientToPatientEntity(Patient patient){
        if (patient == null){
            return null;
        }
        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setPatientId(patient.getPatientId());
        patientEntity.setPatientName(patient.getPatientName());
        patientEntity.setPatientCondition(patient.getPatientCondition());
        return patientEntity;
    }

    @Override
    public Patient patientEntityToPatient(PatientEntity patientEntity){
        if (patientEntity == null){
            return null;
        }
        Patient patient = new Patient();
        patient.setPatientId(patientEntity.getPatientId());
        patient.setPatientName(patientEntity.getPatientName());
        patient.setPatientCondition(patientEntity.getPatientCondition());
        return patient;
    }

    @Override
    public TreatmentEntity treatmentToTreatmentEntity(Treatment treatment){
        if (treatment == null){
            return null;
        }
        TreatmentEntity treatmentEntity = new TreatmentEntity();
        treatmentEntity.setTreatmentId(treatment.getTreatmentId());
        treatmentEntity.setTreatmentName(treatment.getTreatmentName());
        treatmentEntity.setTreatmentType(treatment.getTreatmentType());
        return treatmentEntity;
    }

    @Override
    public Treatment treatmentEntityToTreatment(TreatmentEntity treatmentEntity){
        if (treatmentEntity == null){
            return null;
        }
        Treatment treatment = new Treatment();
        treatment.setTreatmentId(treatmentEntity.getTreatmentId());
        treatment.setTreatmentName(treatmentEntity.getTreatmentName());
        treatment.setTreatmentType(treatmentEntity.getTreatmentType());
        return treatment;
    }
}