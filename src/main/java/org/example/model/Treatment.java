package org.example.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.Validate;

@Getter
@Setter
public class Treatment {
    private final String treatmentId;
    private String treatmentName;
    private String treatmentType;

    public Treatment(String treatmentId, String treatmentName, String treatmentType) {
        this.treatmentId = Validate.notNull(treatmentId);
        this.treatmentName = Validate.notBlank(treatmentName);
        this.treatmentType = Validate.notBlank(treatmentType);
    }
}
