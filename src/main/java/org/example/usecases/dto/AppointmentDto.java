package org.example.usecases.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AppointmentDto {

    @JsonProperty("id")
    private String appointmentId;

    @JsonProperty("datetime")
    private LocalDateTime appointmentDate;

    @JsonProperty("doctorname")
    private String doctorName;

    @JsonProperty("patientname")
    private String patientName;

    @JsonProperty("treatmentName")
    private String treatmentName;

}
