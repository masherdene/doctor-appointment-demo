package org.example.usecases;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.ObjectUtils;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
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


public class CreateAppointment {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final TreatmentRepository treatmentRepository;
    private final static AtomicInteger id = new AtomicInteger();

    public CreateAppointment(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository, PatientRepository patientRepository, TreatmentRepository treatmentRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.treatmentRepository = treatmentRepository;
    }

    public List<String> execute(LocalDateTime appointmentDateTime, String patientId, String doctorId, List<String> treatmentIds) throws UseCaseException {
        existsCheck(patientId, doctorId, treatmentIds);
        try {
//            String appointmentId = UUID.randomUUID().toString();
            String appointmentId = generateNumericId();
            Appointment appointment = new Appointment(appointmentId, appointmentDateTime, doctorId, patientId, treatmentIds);
            appointmentRepository.addAppointment(appointment);
            return List.of(appointment.getAppointmentId(),appointment.getAppointmentDateTime().format(CUSTOM_FORMATTER));
        }
        catch (RuntimeException e){
            throw new UseCaseException("appointment not created");
        }
    }

    private void existsCheck(String patientId, String doctorId, List<String> treatmentIds) throws UseCaseException {
       Patient patient = patientRepository.findPatientById(Validate.notBlank(patientId));
       Doctor doctor = doctorRepository.findDoctorById(Validate.notBlank(doctorId));
       List<Treatment> treatment = treatmentRepository.findTreatmentsByIds(Validate.notNull(treatmentIds));
       if (ObjectUtils.anyNull(patient,doctor) || ObjectUtils.anyNull(treatment)) {
            throw new UseCaseException("id not found");
        }
    }

    private String generateNumericId(){
        return String.valueOf(id.incrementAndGet());
    }

}
