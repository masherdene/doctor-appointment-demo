package org.example.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.Validate;

@Getter
@Setter
public class Treatment {
    private final String treatmentId;
    private String treatmentName;
    private String specialization;

    public Treatment(String treatmentId, String treatmentName, String specialization) {
        this.treatmentId = Validate.notNull(treatmentId);
        this.treatmentName = Validate.notBlank(treatmentName);
        this.specialization = Validate.notBlank(specialization);
    }
}
