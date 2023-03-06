package org.example.infrastructure.mapper;

import org.example.infrastructure.entity.AppointmentEntity;
import org.example.infrastructure.repository.AppointmentRepository;
import org.example.model.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModelEntityService {

    AppointmentRepository appointmentRepository;

    ModelEntityMapperImpl modelEntityMapper;
    @Autowired
    public ModelEntityService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Transactional
    public List<Appointment> getAllAppointments() {
        return (appointmentRepository.findAll())
                .stream()
                .map(modelEntityMapper::appointmentEntityToAppointment)
                .collect(Collectors.toList());
    }

    @Transactional
    public Appointment getAppointment(int appointmentId){
        AppointmentEntity appointmentEntity = this.appointmentRepository.findAppointmentById(appointmentId);
        Appointment appointment = modelEntityMapper.appointmentEntityToAppointment(appointmentEntity);
        return appointment;
    }

    @Transactional
    public void save(Appointment appointment){
        AppointmentEntity appointmentEntity = modelEntityMapper.appointmentToAppointmentEntity(appointment);
        this.appointmentRepository.save(appointmentEntity);
    }

    @Transactional
    public void delete(int appointmentId){
        this.appointmentRepository.deleteById(appointmentId);
    }

}
