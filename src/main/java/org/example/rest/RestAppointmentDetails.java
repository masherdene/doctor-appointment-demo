package org.example.rest;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class RestAppointmentDetails {
    private String patientName;
    private String patientCondition;
    private String doctorName;
    private String doctorSpecialization;
    private List<String> treatmentNames;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTime;
}
