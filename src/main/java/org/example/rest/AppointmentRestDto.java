package org.example.rest;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.List;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentRestDto {

    @JsonProperty("appointmentId")
    private String appointmentId;
    @NonNull
    @JsonProperty("patientId")
    private String patientId;
    @NonNull
    @JsonProperty("patientCondition")
    private String patientCondition;
    @NonNull
    @JsonProperty("doctorId")
    private String doctorId;
    @NonNull
    @JsonProperty("doctorSpecialization")
    private String doctorSpecialization;
    @NonNull
    @JsonProperty("treatmentIds")
    private List<String> treatmentIds;
    @NonNull
    @JsonProperty("appointmentDateTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime appointmentDateTime;
}
