package org.example.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

@Entity
@Table(name = "treatment")
@Getter
@Setter
public class TreatmentEntity {

    @Id
    private int treatmentId;

    @OneToMany(mappedBy = "treatment")
    public ArrayList<TreatmentEntity> treatments;

    @Column(name = "treatmentName")
    private String treatmentName;

    @Column(name = "treatmentType")
    private String treatmentType;
}
