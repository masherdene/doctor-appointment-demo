package org.example.infrastructure.mapper;

import org.example.infrastructure.entity.AppointmentEntity;
import org.example.infrastructure.entity.DoctorEntity;
import org.example.infrastructure.entity.PatientEntity;
import org.example.infrastructure.entity.TreatmentEntity;
import org.example.model.Appointment;
import org.example.model.Doctor;
import org.example.model.Patient;
import org.example.model.Treatment;

//@Mapper(componentModel = "spring")
public interface ModelEntityMapper {
    Appointment appointmentEntityToAppointment(AppointmentEntity appointmentEntity);
    AppointmentEntity appointmentToAppointmentEntity(Appointment appointment);

    DoctorEntity doctorToDoctorEntity(Doctor doctor);
    Doctor doctorEntityToDoctor(DoctorEntity doctorEntity);

    PatientEntity patientToPatientEntity(Patient patient);
    Patient patientEntityToPatient(PatientEntity patientEntity);

    TreatmentEntity treatmentToTreatmentEntity(Treatment treatement);
    Treatment treatmentEntityToTreatment(TreatmentEntity treatmentEntity);
}
