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

    public String execute(String doctorId, String patientId, List<String> treatmentIds) throws RuntimeException {
        try {
            existsCheck(doctorId,patientId,treatmentIds);
            String appointmentId = UUID.randomUUID().toString();
            Appointment appointment = new Appointment(appointmentId, doctorId, patientId, treatmentIds);
            appointmentRepository.addAppointment(appointment);
            return appointment.getAppointmentId();
        }
        catch (RuntimeException e){
            throw new RuntimeException();
        }
    }

    private void existsCheck(String doctorId, String patientId, List<String> treatmentIds){
        Doctor doctor = doctorRepository.findDoctorById(Validate.notBlank(doctorId));
        Patient patient = patientRepository.findPatientById(Validate.notBlank(patientId));
        List<Treatment> treatments = treatmentRepository.findTreatmentsByIds(Validate.notNull(treatmentIds));
        if (ObjectUtils.anyNull(doctor,patient) || ObjectUtils.anyNull(treatments))
        {
            throw new RuntimeException();
        }
    }

}
