package org.example.repository;

import org.example.model.Appointment;
import java.util.List;

// For now repository works with domain model. Later repository should work with domain entities.
public interface AppointmentRepository {
    Appointment findAppointmentById(String appointmentId);
    List<Appointment> findAllAppointments();
    void deleteAppointmentById(String appointmentId);
    void addAppointment(Appointment appointment);

}
