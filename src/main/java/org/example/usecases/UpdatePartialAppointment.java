package org.example.usecases;

import org.example.model.Appointment;
import org.example.repository.AppointmentRepository;
import org.example.repository.DoctorRepository;
import org.example.repository.PatientRepository;
import org.example.repository.TreatmentRepository;
import org.example.usecases.exception.UseCaseException;
import static org.example.rest.AppointmentController.CUSTOM_FORMATTER;
import java.time.LocalDateTime;
import java.util.List;


public class UpdatePartialAppointment {

    private AppointmentRepository appointmentRepository;
    private DoctorRepository doctorRepository;
    private PatientRepository patientRepository;
    private TreatmentRepository treatmentRepository;

    public UpdatePartialAppointment(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository, PatientRepository patientRepository, TreatmentRepository treatmentRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.treatmentRepository = treatmentRepository;
    }

    public List<String> execute(String appointmentId, LocalDateTime newAppointmentDateTime) throws UseCaseException {
        Appointment appointment = appointmentRepository.findAppointmentById(appointmentId);
        if(appointment == null){
            new UseCaseException("appointment not found");
        }
        try {
            appointment.setAppointmentDateTime(newAppointmentDateTime);
            appointmentRepository.updateAppointment(appointmentId,appointment);
            return List.of(appointment.getAppointmentId(),appointment.getAppointmentDateTime().format(CUSTOM_FORMATTER));
        } catch (RuntimeException e) {
            throw new UseCaseException("not updated");
        }
    }

}


