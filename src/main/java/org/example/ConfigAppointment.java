package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.repository.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(
        basePackages = {"org.example.rest"}
)
public class ConfigAppointment {

    @Bean
    public AppointmentRepository appointmentRepository() {
        return new AppointmentRepositoryImpl();
    }
    @Bean
    public DoctorRepository doctorRepository() {
        return new DoctorRepositoryImpl();
    }
    @Bean
    public PatientRepository patientRepository() {
        return new PatientRepositoryImpl();
    }
    @Bean
    public TreatmentRepository treatmentRepository() {
        return new TreatmentRepositoryImpl();
    }

    @Bean
    public ObjectMapper defaultMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

}
