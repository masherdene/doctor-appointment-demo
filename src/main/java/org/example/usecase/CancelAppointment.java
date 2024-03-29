package org.example.usecase;

import org.apache.commons.lang3.Validate;
import org.example.model.Appointment;
import org.example.repository.AppointmentRepository;
import org.example.usecase.exception.UseCaseException;

public class CancelAppointment {

    private AppointmentRepository appointmentRepository;

    public CancelAppointment(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public String execute(String appointmentId) throws UseCaseException {
        Validate.notNull(Validate.notBlank(appointmentId));
        Appointment appointment = appointmentRepository.findAppointmentById(appointmentId);
        if(appointment==null){
            new UseCaseException("not found");
        }
        try {
            appointmentRepository.deleteAppointmentById(appointmentId);
            return "deleted appointment with id" + appointmentId;
        } catch (RuntimeException e) {
            throw new UseCaseException("cannot delete appointment");
        }
    }


}
