package org.example;

import org.example.repository.AppointmentRepository;
import org.example.repository.AppointmentRepositoryImpl;

@Configiguration
public class BeanConfig {
    @Bean
    public AppointmentRepository appointmentRepository()
        return new AppointmentRepositoryImpl();
    }

}
