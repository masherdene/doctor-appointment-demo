package org.example.usecases;

import org.example.infrastructure.mapper.ModelEntityService;
import org.example.model.Appointment;
import org.example.infrastructure.repository.AppointmentRepository;
import java.time.LocalDateTime;

public class CreateAppointment {

    private ModelEntityService modelEntityService;

    public CreateAppointment(ModelEntityService modelEntityService){
        this.modelEntityService = modelEntityService;
    }

    public int execute(String doctorName, String patientName, String treatmentName) throws Exception {
        try {
            Appointment appointment = new Appointment(0, LocalDateTime.now(), doctorName, patientName, treatmentName);
//            modelEntityService.getAllAppointments();
            return appointment.getAppointmentId();
        }
        catch (Exception e){
            System.out.println("Error");
            return 0;
        }
    }
}
