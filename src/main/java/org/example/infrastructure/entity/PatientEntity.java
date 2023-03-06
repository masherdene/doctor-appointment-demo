package org.example.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Entity
@Table(name = "patient")
@Getter
@Setter
public class PatientEntity {

    @Id
    private int patientId;

    @OneToMany(mappedBy = "patient")
    private ArrayList<PatientEntity> patients;

    @Column(name = "patientName")
    private String patientName;

    @Column(name = "patientEmail")
    private String patientEmail;

    @Column(name = "patientCondition")
    private String patientCondition;
}
