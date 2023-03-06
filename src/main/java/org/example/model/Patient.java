package org.example.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Patient {
    private int patientId;
    private String patientName;
    private String patientEmail;
    private String patientCondition;
}
