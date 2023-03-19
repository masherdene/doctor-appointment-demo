package org.example.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.Validate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class Appointment {
    private final String appointmentId;                             // declared final because it's unique non-changable identifier
    private LocalDateTime appointmentDateTime;
    private final String doctorId;                                  // declared final because it's unique non-changable identifier
    private final String patientId;                                 // declared final because it's unique non-changable identifier
    private final List<String> treatmentIds;

    public Appointment(String appointmentId, LocalDateTime appointmentDateTime, String doctorId, String patientId, List<String> treatmentIds) {
        this.appointmentId = Validate.notBlank(appointmentId);
        this.appointmentDateTime = Validate.notNull(appointmentDateTime);
        this.doctorId = Validate.notBlank(doctorId);
        this.patientId = Validate.notBlank(patientId);
        this.treatmentIds = Validate.notNull(treatmentIds);
    }

}