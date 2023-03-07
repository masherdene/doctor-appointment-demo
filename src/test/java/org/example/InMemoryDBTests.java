package org.example;

import org.example.repository.AppointmentRepository;
import org.example.model.Appointment;
import org.example.model.Treatment;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;


class InMemoryDBTests {

//    private AppointmentRepository appointmentRepository;
//
//    @Test
//    @Sql("create.sql")
//    void AppointmentIsNotNull() {
//        Appointment appointment = appointmentRepository.findAppointmentById(1);
//        assertThat(appointment).isNotNull();
//    }
//
//    @Test
//    void AppointmentSaveAndGetName(){
//        List<Treatment> treatments = new ArrayList<>();
//        treatments.add(new Treatment(1));
//        treatments.add(new Treatment(2));
//        Appointment appointment = new Appointment(1,1,1,treatments);
//        appointmentRepository.save(appointment);
//
//        Appointment appoint = appointmentRepository.findAppointmentById(1);
//        assertEquals(1,appoint.getAppointmentId());
//    }

}
