package org.example;

import org.example.model.Appointment;
import org.example.model.Doctor;
import org.example.model.Patient;
import org.example.model.Treatment;
import org.example.repository.AppointmentRepository;
import org.example.repository.DoctorRepository;
import org.example.repository.PatientRepository;
import org.example.repository.TreatmentRepository;
import org.example.usecases.CreateAppointment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.isA;


@ExtendWith(MockitoExtension.class)
public class CreateAppointmentTest {
    AppointmentRepository appointmentRepository;
    DoctorRepository doctorRepository;
    PatientRepository patientRepository;
    TreatmentRepository treatmentRepository;
    CreateAppointment createAppointment;

    @BeforeEach
    public void SetUp() {
        this.appointmentRepository = Mockito.mock(AppointmentRepository.class);
        this.doctorRepository = Mockito.mock(DoctorRepository.class);
        this.patientRepository = Mockito.mock(PatientRepository.class);
        this.treatmentRepository = Mockito.mock(TreatmentRepository.class);
        this.createAppointment = getSut();
    }

    private CreateAppointment getSut() {                                                                                // SUT: System Under Test
        return new CreateAppointment(appointmentRepository, doctorRepository, patientRepository, treatmentRepository);
    }

    @Test
    public void constructorThrowsExceptionForNullArgs() {
        assertThrows(NullPointerException.class,()-> {CreateAppointment createAppointment
                = new CreateAppointment(null, null, null,null);});
    }

    @Test
    public void executeThrowsException(){
        assertThrows(RuntimeException.class,
                ()->{createAppointment.execute("someid","anotherid", new ArrayList<>(3));}      // will create null instances for mocked objects
        );
    }
    @Test
    public void executeThrowsExceptionForNullObjects(){
        Doctor doctor = mock(Doctor.class);
        Patient patient = mock(Patient.class);
        List<String> treatmentIds = new ArrayList<>();
        treatmentIds.add("3001");
        treatmentIds.add("3002");
        List<Treatment> treatments = new ArrayList<>(2);
        when(doctorRepository.findDoctorById("doctorid")).thenReturn(doctor);
        when(patientRepository.findPatientById("patientid")).thenReturn(patient);
        when(treatmentRepository.findTreatmentsByIds(treatmentIds)).thenReturn(treatments);
        assertThrows(RuntimeException.class,
                ()->{createAppointment.execute("doctorid","patientid",treatmentIds);}
        );
    }

    @Test
    public void executeGetsDoctor(){
        when(doctorRepository.findDoctorById("doctorid")).thenReturn(mock(Doctor.class));
        Doctor doctorMock = doctorRepository.findDoctorById("doctorid");
        Doctor doctor = new Doctor("doctorid","doctorname","doctorspecial");
        assertEquals(doctor.getClass(),doctorMock.getClass());
    }

    @Test
    public void executeAddsAppointment(){
        Appointment appointment = mock(Appointment.class);
        doNothing().when(appointmentRepository).addAppointment(appointment);
        verify(appointmentRepository, times(1)).addAppointment(appointment);               // mocking the object testing?
    }

}
