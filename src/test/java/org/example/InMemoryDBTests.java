package org.example;

import org.example.infrastructure.repository.AppointmentRepository;
import org.example.model.Appointment;
import org.example.model.Treatment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class InMemoryDBTests {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Test
    @Sql("create.sql")
    void AppointmentIsNotNull() {
        Appointment appointment = appointmentRepository.findAppointmentById(1);
        assertThat(appointment).isNotNull();
    }

    @Test
    void AppointmentSaveAndGetName(){
        List<Treatment> treatments = new ArrayList<>();
        treatments.add(new Treatment(1));
        treatments.add(new Treatment(2));
        Appointment appointment = new Appointment(1,1,1,treatments);
        appointmentRepository.save(appointment);

        Appointment appoint = appointmentRepository.findAppointmentById(1);
        assertEquals(1,appoint.getAppointmentId());
    }

}
