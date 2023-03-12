package org.example.usecases;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.Validate;
import java.time.LocalDateTime;
import java.util.List;
import org.example.model.Appointment;
import org.example.model.Doctor;
import org.example.model.Patient;
import org.example.model.Treatment;
import org.example.repository.AppointmentRepository;
import org.example.repository.DoctorRepository;
import org.example.repository.PatientRepository;
import org.example.repository.TreatmentRepository;
import org.example.usecases.exception.UseCaseException;
import static org.example.rest.AppointmentController.CUSTOM_FORMATTER;

public class UpdateAppointment {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final TreatmentRepository treatmentRepository;

    public UpdateAppointment(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository, PatientRepository patientRepository, TreatmentRepository treatmentRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.treatmentRepository = treatmentRepository;
    }

    public List<String> execute(String appointmentId, LocalDateTime appointmentDateTime, String patientId, String doctorId, List<String> treatmentIds) throws UseCaseException {
        existsCheck(appointmentId, patientId, doctorId, treatmentIds);
        try {
            Appointment newAppointment = new Appointment(appointmentId, appointmentDateTime, doctorId, patientId, treatmentIds);
            appointmentRepository.updateAppointment(appointmentId,newAppointment);
            return List.of(newAppointment.getAppointmentId(),newAppointment.getAppointmentDateTime().format(CUSTOM_FORMATTER));
        } catch (RuntimeException e) {
            throw new UseCaseException("not updated");
        }
    }

    private void existsCheck(String appointmentId, String patientId, String doctorId, List<String> treatmentIds) throws UseCaseException {
        Appointment appointment = appointmentRepository.findAppointmentById(Validate.notBlank(appointmentId));
        Patient patient = patientRepository.findPatientById(Validate.notBlank(patientId));
        Doctor doctor = doctorRepository.findDoctorById(Validate.notBlank(doctorId));
        List<Treatment> treatment = treatmentRepository.findTreatmentsByIds(Validate.notNull(treatmentIds));
        if (ObjectUtils.anyNull(appointment,patient,doctor) || ObjectUtils.anyNull(treatment)) {
            throw new UseCaseException("id not found");
        }
    }

}
