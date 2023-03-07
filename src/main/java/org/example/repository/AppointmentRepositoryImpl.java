package org.example.repository;

import org.example.model.Appointment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppointmentRepositoryImpl implements AppointmentRepository {

    private final Map<String, Appointment> appointmentDb = new HashMap<>();

    @Override
    public Appointment findAppointmentById(String appointmentId){
        return this.appointmentDb.get(appointmentId);
    }

    @Override
    public List<Appointment> findAllAppointments(){
        return new ArrayList<>(this.appointmentDb.values());
    }

    @Override
    public void addAppointment(Appointment appointment){
        this.appointmentDb.put(appointment.getAppointmentId(),appointment);
    }

    @Override
    public void deleteAppointmentById(String appointmentId){
        this.appointmentDb.remove(appointmentId);
    }

}
