package org.example.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.Validate;

@Getter
@Setter
public class Doctor {
    private final String doctorId;
    private String doctorName;
    private String doctorSpecialization;

    public Doctor(String doctorId, String doctorName, String doctorSpecialization) {
        this.doctorId = Validate.notBlank(doctorId);
        this.doctorName = Validate.notBlank(doctorName);
        this.doctorSpecialization = Validate.notBlank(doctorSpecialization);
    }
}
