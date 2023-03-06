package org.example.usecases;

import org.example.infrastructure.repository.DoctorRepository;
import org.example.infrastructure.repository.PatientRepository;
import org.example.infrastructure.repository.TreatmentRepository;
import org.example.infrastructure.repository.AppointmentRepository;
import org.example.model.Appointment;
import org.example.model.Doctor;
import org.example.model.Patient;
import org.example.model.Treatment;
import org.apache.commons.lang3.ObjectUtils;
import org.example.usecases.helper.IdentifierGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// use cases as beans?
@Component
public class CreateAppointment {

    private AppointmentRepository appointmentRepository;
    private DoctorRepository doctorRepository;
    private PatientRepository patientRepository;
    private TreatmentRepository treatmentRepository;

    @Autowired
    public CreateAppointment(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository, PatientRepository patientRepository, TreatmentRepository treatmentRepository){
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.treatmentRepository = treatmentRepository;
    }

    public int execute(int doctorId, int patientId, int treatmentId) throws Exception {
        try {
            Optional<Doctor> doctor = doctorRepository.findById(doctorId);
            Optional<Patient> patient = patientRepository.findById(patientId);
            List<Treatment> treatments = treatmentRepository.findAllByTreatmentId(treatmentId);
            if (ObjectUtils.anyNull(doctor,patient,treatments))
            {
                throw new Exception();
            }
            Integer appointmentId = IdentifierGenerator.generateUniqueId();
            Appointment appointment = new Appointment(appointmentId, doctorId, patientId, treatments);
            appointmentRepository.save(appointment);
            return appointment.getAppointmentId();
        }
        catch (Exception e){
            System.out.println("Error");
            return 1;
        }
    }
}
