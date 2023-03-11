package org.example.rest;

import org.example.repository.*;
import org.example.usecases.CancelAppointment;
import org.example.usecases.CreateAppointment;
import org.example.usecases.GetAppointment;
import org.example.usecases.UpdatePartialAppointment;
import org.example.usecases.exception.UseCaseException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
public class AppointmentController {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final TreatmentRepository treatmentRepository;
    public final static DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public AppointmentController(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository, PatientRepository patientRepository, TreatmentRepository treatmentRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.treatmentRepository = treatmentRepository;
    }

    @PostMapping("/newappointment")
    public ResponseEntity<List<String>> create(@RequestBody RestAppointmentDetails body) throws UseCaseException {
        try {
          CreateAppointment createAppointment = new CreateAppointment(appointmentRepository,doctorRepository,patientRepository,treatmentRepository);
          List<String> appointment = createAppointment.execute(body.getAppointmentDateTime(), body.getPatientId(),body.getDoctorId(), body.getTreatmentIds());
          return ResponseEntity.status(HttpStatus.CREATED).body(appointment);
        }
        catch (UseCaseException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(List.of(e.getMessage()));
        }
    }

    @RequestMapping("/appointment/{id}")
    public ResponseEntity<List<String>> read(@PathVariable String id){
        try{
            GetAppointment getAppointment = new GetAppointment(appointmentRepository,doctorRepository,patientRepository,treatmentRepository);
            List<String> appointment = getAppointment.execute(id);
            return ResponseEntity.status(HttpStatus.FOUND).body(appointment);
        } catch (UseCaseException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(List.of(e.getMessage()));
        }
    }

    @DeleteMapping("/appointment")
    public ResponseEntity<String> delete(@RequestParam(value = "id", defaultValue = "1") String id){
        try {
            CancelAppointment cancelAppointment = new CancelAppointment(appointmentRepository,doctorRepository,patientRepository,treatmentRepository);
            String appointment = cancelAppointment.execute(id);
            return ResponseEntity.status(HttpStatus.OK).body(appointment);
        } catch (UseCaseException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PatchMapping("/update")
    public ResponseEntity <List<String>> updatePartial(@RequestParam(value = "id") String id, @RequestParam(value = "datetime") String datetime){
        try {
            UpdatePartialAppointment updatePartialAppointment = new UpdatePartialAppointment(appointmentRepository,doctorRepository,patientRepository,treatmentRepository);
            List<String> appointment = updatePartialAppointment.execute(id, LocalDateTime.parse(datetime, CUSTOM_FORMATTER));
            return ResponseEntity.status(HttpStatus.CREATED).body(appointment);
        } catch (UseCaseException e) {
            return ResponseEntity.status(HttpStatus.CREATED).body(List.of(e.getMessage()));
        }
    }
}
