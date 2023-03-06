package org.example.infrastructure.repository;

import org.example.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// For now repository works with domain model. Later repository should work with domain entities.
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    Appointment findAppointmentById(int appointmentId);
    List<Appointment> findAllAppointments();
    Appointment deleteAppointmentById(int appointmentId);
}
