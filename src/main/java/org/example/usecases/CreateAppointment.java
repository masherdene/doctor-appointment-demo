package org.example.usecases;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.ObjectUtils;
import org.example.model.Appointment;
import org.example.model.Doctor;
import org.example.model.Patient;
import org.example.model.Treatment;
import org.example.repository.AppointmentRepository;
import org.example.repository.DoctorRepository;
import org.example.repository.PatientRepository;
import org.example.repository.TreatmentRepository;
import java.util.List;
import java.util.UUID;

// use cases as beans?
public class CreateAppointment {

    private AppointmentRepository appointmentRepository;
    private DoctorRepository doctorRepository;
    private PatientRepository patientRepository;
    private TreatmentRepository treatmentRepository;

    public CreateAppointment(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository, PatientRepository patientRepository, TreatmentRepository treatmentRepository){
        this.appointmentRepository = Validate.notNull(appointmentRepository);
        this.doctorRepository = Validate.notNull(doctorRepository);
        this.patientRepository = Validate.notNull(patientRepository);
        this.treatmentRepository = Validate.notNull(treatmentRepository);
    }

    public String execute(String doctorId, String patientId, List<String> treatmentIds) throws Exception {
        try {
            Doctor doctor = doctorRepository.findDoctorById(doctorId);
            Patient patient = patientRepository.findPatientById(patientId);
            List<Treatment> treatments = treatmentRepository.findTreatmentsByIds(treatmentIds);
            if (ObjectUtils.anyNull(doctor,patient,treatments))
            {
                throw new Exception();
            }
            String appointmentId = UUID.randomUUID().toString();
            Appointment appointment = new Appointment(appointmentId, doctorId, patientId, treatmentIds);
            appointmentRepository.addAppointment(appointment);
            return appointment.getAppointmentId();
        }
        catch (Exception e){
            return "Error";
        }
    }
}
