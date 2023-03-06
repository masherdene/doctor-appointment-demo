package org.example.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

@Entity
@Table(name = "doctor")
@Getter
@Setter
public class DoctorEntity {

    @Id
    private int doctorId;

    @OneToMany(mappedBy = "doctor")                                  // child entity owns the bidirectional relationship
    private ArrayList<DoctorEntity> doctors;

    @Column(name = "doctorName")
    private String doctorName;

    @Column(name = "doctorSpecialization")
    private String doctorSpecialization;

}