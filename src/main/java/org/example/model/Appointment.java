package org.example.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.Validate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class Appointment {
    private final int appointmentId;                             // declared final because it's unique non-changable identifier

    private LocalDateTime appointmentDate;
    private final int doctorId;                                  // declared final because it's unique non-changable identifier
    private final int patientId;                                 // declared final because it's unique non-changable identifier
    private List<Treatment> treatments;

    public Appointment(int appointmentId, int doctorId, int patientId, List<Treatment> treatments) {
        this.appointmentId = Validate.notNull(appointmentId);
        this.doctorId = Validate.notNull(doctorId);
        this.patientId = Validate.notNull(patientId);
        this.treatments = Validate.notNull(treatments);
    }

}