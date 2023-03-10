package org.example.rest;

import org.example.repository.*;
import org.example.usecases.CreateAppointment;
import org.example.usecases.exception.UseCaseException;
import org.springframework.web.bind.annotation.*;

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
    public String create(@RequestBody RestAppointmentDetails body) throws UseCaseException {
        CreateAppointment createAppointment = new CreateAppointment(appointmentRepository,doctorRepository,patientRepository,treatmentRepository);
                try {
          String appointment = createAppointment.execute(body.getPatientId(),body.getDoctorId(), body.getTreatmentIds());
          return appointment;
        } catch (UseCaseException e) {
//            throw http exception for 404
          throw new UseCaseException();
        }
    }

}
