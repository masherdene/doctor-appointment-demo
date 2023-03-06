package org.example.rest;

import org.example.infrastructure.entity.AppointmentEntity;
import org.example.infrastructure.mapper.ModelEntityMapperImpl;
import org.example.infrastructure.repository.AppointmentRepository;
import org.example.model.Appointment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class UserController {

    private ModelEntityMapperImpl modelEntityMapper;
    private AppointmentRepository appointmentRepository;


    @GetMapping("/{all}")
    List<Appointment> getAllAppointment(){
//        List<Appointment> appointment = modelEntityMapper.getAllAppointments();
        return appointment;
    }

    @GetMapping("/{id}")
    AppointmentEntity getAppointmentById(@RequestParam Long appointmentId){
//        AppointmentEntity appointmentEntity = appointmentRepository.findById(appointmentId);
        return appointmentEntity;
    }
}
