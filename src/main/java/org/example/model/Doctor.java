package org.example.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.Validate;

@Getter
@Setter
@NoArgsConstructor
public class Doctor {

    private final int doctorId;
    private String doctorName;
    private String doctorSpecialization;

    public Doctor(int doctorId, String doctorName, String doctorSpecialization) {
        this.doctorId = Validate.notNull(doctorId);
        this.doctorName = Validate.notBlank(doctorName);
        this.doctorSpecialization = Validate.notBlank(doctorSpecialization);
    }
}
