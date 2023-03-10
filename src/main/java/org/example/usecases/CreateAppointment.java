package org.example.usecases;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.ObjectUtils;
import org.example.model.Appointment;
import org.example.model.Doctor;
import org.example.model.Patient;
import org.example.model.Treatment;
import org.example.repository.AppointmentRepository;
import org.example.repository.DoctorRepository;
import org.example.repository.PatientRepository;
import org.example.repository.TreatmentRepository;
import org.example.usecases.exception.UseCaseException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CreateAppointment {

    private AppointmentRepository appointmentRepository;
    private DoctorRepository doctorRepository;
    private PatientRepository patientRepository;
    private TreatmentRepository treatmentRepository;

    private final static DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public CreateAppointment(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository, PatientRepository patientRepository, TreatmentRepository treatmentRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.treatmentRepository = treatmentRepository;
    }

    public List<String> execute(LocalDateTime appointmentDateTime, String patientId, String doctorId, List<String> treatmentIds) throws UseCaseException {
        try {
            existsCheck(patientId, doctorId, treatmentIds);
            String appointmentId = UUID.randomUUID().toString();
            Appointment appointment = new Appointment(appointmentId, appointmentDateTime, doctorId, patientId, treatmentIds);
            appointmentRepository.addAppointment(appointment);
            List<String> output = new ArrayList<>();
            output.add(appointment.getAppointmentId());
            output.add(appointment.getAppointmentDateTime().format(CUSTOM_FORMATTER));
            return output;
        }
        catch (RuntimeException e){
            throw new UseCaseException("not created");
        }
    }

    private void existsCheck(String patientId, String doctorId, List<String> treatmentIds) throws UseCaseException {
       Patient patient = patientRepository.findPatientById(Validate.notBlank(patientId));
       Doctor doctor = doctorRepository.findDoctorById(Validate.notBlank(doctorId));
       List<Treatment> treatment = treatmentRepository.findTreatmentsByIds(Validate.notNull(treatmentIds));
       if (ObjectUtils.anyNull(patient) || ObjectUtils.anyNull(doctor) || ObjectUtils.anyNull(treatment)) {
            throw new UseCaseException("id not found");
        }
    }

}
