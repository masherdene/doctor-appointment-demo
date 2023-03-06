package org.example.infrastructure.repository;

import org.example.infrastructure.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// Q: repository should work with domain entities?
public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {
    AppointmentEntity findAppointmentById(int appointmentId);
    List<AppointmentEntity> findAllAppointments();
    AppointmentEntity deleteAppointmentById(int appointmentId);
}
