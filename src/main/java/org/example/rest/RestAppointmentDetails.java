package org.example.rest;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class RestAppointmentDetails {

    private String appointmentId;
    private String patientId;
    private String patientCondition;
    private String doctorId;
    private String doctorSpecialization;
    private List<String> treatmentIds;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime appointmentDateTime;
}
