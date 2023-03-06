package org.example.rest;

import org.example.infrastructure.repository.AppointmentRepository;
import org.example.model.Appointment;
import org.example.usecases.CreateAppointment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping(value = "/index")
public class AppointmentController {

//    private ModelEntityMapperImpl modelEntityMapper;
    private AppointmentRepository appointmentRepository;

    @GetMapping("/{create}")
    List<Appointment> getAllAppointment(int doctorId, int patientId, int treatmentId){
        CreateAppointment createAppointment = new CreateAppointment();
        Appointment appointment = createAppointment.execute(doctorId, patientId, treatmentId);
        return appointment;
    }
//
//    @GetMapping("/{all}")
//    List<Appointment> getAllAppointment(){
////        List<Appointment> appointment = modelEntityMapper.getAllAppointments();
//        return appointment;
//    }
//
//    @GetMapping("/{id}")
//    AppointmentEntity getAppointmentById(@RequestParam Long appointmentId){
////        AppointmentEntity appointmentEntity = appointmentRepository.findById(appointmentId);
//        return appointmentEntity;
//    }
}
