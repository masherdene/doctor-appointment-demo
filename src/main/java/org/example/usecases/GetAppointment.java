package org.example.usecases;

import org.apache.commons.lang3.Validate;
import java.util.List;
import org.example.model.Appointment;
import org.example.repository.AppointmentRepository;
import org.example.usecases.exception.UseCaseException;
import static org.example.rest.AppointmentController.CUSTOM_FORMATTER;


public class GetAppointment {

    private AppointmentRepository appointmentRepository;

    public GetAppointment(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public List<String> execute(String appointmentId) throws UseCaseException {
        Validate.notNull(appointmentId);
        Appointment appointment = appointmentRepository.findAppointmentById(appointmentId);
        if (appointment == null) {
            throw new UseCaseException("not found");
        }
        try {
            return List.of(appointment.getAppointmentId(),appointment.getAppointmentDateTime().format(CUSTOM_FORMATTER));
        } catch (RuntimeException e) {
            throw new UseCaseException("cannot get appointment");
        }
    }
}
