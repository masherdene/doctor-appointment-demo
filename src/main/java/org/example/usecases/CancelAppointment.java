package org.example.usecases;

import org.apache.commons.lang3.Validate;
import org.example.model.Appointment;
import org.example.repository.AppointmentRepository;
import org.example.repository.DoctorRepository;
import org.example.repository.PatientRepository;
import org.example.repository.TreatmentRepository;
import org.example.usecases.exception.UseCaseException;

public class CancelAppointment {

    private AppointmentRepository appointmentRepository;
    private DoctorRepository doctorRepository;
    private PatientRepository patientRepository;
    private TreatmentRepository treatmentRepository;

    public CancelAppointment(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository, PatientRepository patientRepository, TreatmentRepository treatmentRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.treatmentRepository = treatmentRepository;
    }

    public String execute(String appointmentId) throws UseCaseException {
        Validate.notNull(appointmentId);
        Appointment appointment = appointmentRepository.findAppointmentById(appointmentId);
        if(appointment==null){
            new UseCaseException("not found");
        }
        try {
            appointmentRepository.deleteAppointmentById(appointmentId);
            return "deleted appointment with id" + appointmentId;
        } catch (RuntimeException e) {
            throw new UseCaseException("cannot delete appointment");
        }
    }


}
