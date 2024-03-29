package org.example.usecase;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.lang3.Validate;
import org.example.model.Appointment;
import org.example.repository.AppointmentRepository;
import org.example.usecase.exception.UseCaseException;
import static org.example.rest.AppointmentController.CUSTOM_FORMATTER;



public class UpdatePartialAppointment {

    private final AppointmentRepository appointmentRepository;

    public UpdatePartialAppointment(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public List<String> execute(String appointmentId, LocalDateTime newAppointmentDateTime) throws UseCaseException {
        Appointment appointment = appointmentRepository.findAppointmentById(Validate.notBlank(appointmentId));
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


