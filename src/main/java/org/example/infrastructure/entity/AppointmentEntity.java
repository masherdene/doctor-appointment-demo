package org.example.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointment")
@Getter
@Setter
public class AppointmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointmentId")
    private int appointmentId;

    @Column(name = "appointmentDate")
    private LocalDateTime appointmentDate;

    @ManyToOne
    @JoinColumn(name = "doctorId")                                              // mapping on the owner, the child entity
    private DoctorEntity doctor;

    @ManyToOne
    @JoinColumn(name = "patientId")
    private PatientEntity patient;

    @ManyToOne
    @JoinColumn(name = "treatmentId")
    private TreatmentEntity treatment;

}
