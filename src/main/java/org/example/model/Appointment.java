package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Appointment {
    private int appointmentId;
    private LocalDateTime appointmentDate;
    private String doctorName;
    private String patientName;
    private String treatmentName;

    public Appointment() {
    }

    public Appointment(int appointmentId, LocalDateTime appointmentDate, String doctorName, String patientName, String treatmentName) {
        this.appointmentId = appointmentId;
        this.appointmentDate = appointmentDate;
        this.doctorName = doctorName;
        this.patientName = patientName;
        this.treatmentName = treatmentName;
    }

}