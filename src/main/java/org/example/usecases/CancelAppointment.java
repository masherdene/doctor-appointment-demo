package org.example.usecases;

import org.apache.commons.lang3.Validate;
import org.example.model.Appointment;
import org.example.repository.AppointmentRepository;
import org.example.repository.DoctorRepository;
import org.example.repository.PatientRepository;
import org.example.repository.TreatmentRepository;
import org.example.usecases.exception.UseCaseException;

public class CancelAppointment {

    private AppointmentRepository appointmentRepository;

    public CancelAppointment(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public String execute(String appointmentId) throws UseCaseException {
        Validate.notNull(appointmentId);
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
