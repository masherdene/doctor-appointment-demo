package org.example.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.Validate;

@Getter
@Setter
public class Patient {
    private final String patientId;
    private String patientName;
    private String patientEmail;
    private String patientCondition;

    public Patient(String patientId, String patientName, String patientCondition) {
        this.patientId = Validate.notNull(patientId);
        this.patientName = Validate.notBlank(patientName);
        this.patientCondition = Validate.notBlank(patientCondition);
    }
}
