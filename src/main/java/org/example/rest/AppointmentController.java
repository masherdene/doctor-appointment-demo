package org.example.rest;

import org.example.repository.*;
import org.example.usecases.CreateAppointment;
import org.example.usecases.exception.UseCaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AppointmentController {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final TreatmentRepository treatmentRepository;

    public AppointmentController(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository, PatientRepository patientRepository, TreatmentRepository treatmentRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.treatmentRepository = treatmentRepository;
    }

    @PostMapping("/{newappointment}")
    public ResponseEntity<List<String>> create(@RequestBody RestAppointmentDetails body) throws UseCaseException {
        CreateAppointment createAppointment = new CreateAppointment(appointmentRepository,doctorRepository,patientRepository,treatmentRepository);
        try {
          List<String> appointment = createAppointment.execute(body.getAppointmentDateTime(), body.getPatientId(),body.getDoctorId(), body.getTreatmentIds());
          return ResponseEntity.status(HttpStatus.CREATED).body(appointment);
        }
        catch (UseCaseException e) {
            List<String> message = new ArrayList<>(1);
            message.add(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }
}
