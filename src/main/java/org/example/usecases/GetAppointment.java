package org.example.usecases;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.Validate;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.example.model.Appointment;
import org.example.model.Doctor;
import org.example.model.Patient;
import org.example.model.Treatment;
import org.example.repository.AppointmentRepository;
import org.example.repository.DoctorRepository;
import org.example.repository.PatientRepository;
import org.example.repository.TreatmentRepository;
import org.example.usecases.exception.UseCaseException;
import static org.example.rest.AppointmentController.CUSTOM_FORMATTER;

public class GetAppointment {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final TreatmentRepository treatmentRepository;

    public GetAppointment(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository, PatientRepository patientRepository, TreatmentRepository treatmentRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.treatmentRepository = treatmentRepository;
    }

    public List<String> execute(String appointmentId) throws UseCaseException {
        List<String> returnValues = existsCheckAndGetDetails(appointmentId);
        Validate.notNull(appointmentId);
        Appointment appointment = appointmentRepository.findAppointmentById(appointmentId);
        if (appointment == null) {
            throw new UseCaseException("not found");
        }
        try {
            returnValues.addAll(List.of(appointment.getAppointmentId(),appointment.getAppointmentDateTime().format(CUSTOM_FORMATTER)));
            return returnValues;
        } catch (RuntimeException e) {
            throw new UseCaseException("cannot get appointment");
        }
    }

    private List<String> existsCheckAndGetDetails(String appointmentId) throws UseCaseException {
        Appointment appointment = appointmentRepository.findAppointmentById(Validate.notBlank(appointmentId));
        if (appointment==null){
            throw new UseCaseException("appointment id not found");
        }
        Patient patient = patientRepository.findPatientById(Validate.notNull(appointment.getPatientId()));
        Doctor doctor = doctorRepository.findDoctorById(Validate.notNull(appointment.getDoctorId()));
        List<Treatment> treatment = treatmentRepository.findTreatmentsByIds(Validate.notNull(appointment.getTreatmentIds()));
        if (ObjectUtils.anyNull(patient,doctor) || ObjectUtils.anyNull(treatment)) {
            throw new UseCaseException("id not found");
        }
        List<String> details = new LinkedList<>(Arrays.asList(patient.getPatientName(), patient.getPatientCondition(), doctor.getDoctorName()));
        details.addAll(treatment.stream().map(Treatment::getTreatmentName).toList());
        return Collections.unmodifiableList(details);
//        List<String> treatmentNames = treatment.stream().map(Treatment::getTreatmentName).collect(Collectors.toList());
//        return Stream.concat(List.of(patient.getPatientName(),patient.getPatientCondition(),doctor.getDoctorName()).stream(),treatmentNames.stream()).collect(Collectors.toList());
    }
}
