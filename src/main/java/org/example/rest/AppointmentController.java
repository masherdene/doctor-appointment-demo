package org.example.rest;

import org.example.repository.AppointmentRepository;
import org.example.repository.DoctorRepository;
import org.example.repository.PatientRepository;
import org.example.repository.TreatmentRepository;
import org.example.usecases.CreateAppointment;
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
    public String create(@RequestBody RestAppointmentDetails body){

        CreateAppointment createAppointment = new CreateAppointment(appointmentRepository,doctorRepository,treatmentRepository);
        RestAppointmentDetailsMapper mapper = new RestAppointmentDetailsMapper(body.getPatientName(),body.getDoctorName(),body.getTreatmentNames(),body.getDateTime(),patientRepository,doctorRepository,treatmentRepository);
        mapper.modelToRestAppointmentDetails();

        String appointment = createAppointment.execute(mapper.getDoctorId(),mapper.getPatientId(),mapper.getTreatmentIds());
        return appointment;
    }

//    @GetMapping("/{read}")
//    public Appointment read(@PathVariable String appointmentId){
//        Appointment appointment = getAppointment.execute(appointmentId);
//        return appointment;
//    }

}
