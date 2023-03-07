package org.example;

import org.example.repository.AppointmentRepository;
import org.example.usecases.CreateAppointment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreateAppointmentTest {

//    AppointmentRepository appointmentRepository= null;

    @Test
    public void constrCreatesValidInstance()
    {
        CreateAppointment useCase = new CreateAppointment(null, null, null, null);

        Assertions.assertNull(useCase);
    }
}
