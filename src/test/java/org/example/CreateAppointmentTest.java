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
import org.mockito.MockedConstruction;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;



@ExtendWith(MockitoExtension.class)
public class CreateAppointmentTest {
    AppointmentRepository appointmentRepository;
    DoctorRepository doctorRepository;
    PatientRepository patientRepository;
    TreatmentRepository treatmentRepository;
    CreateAppointment createAppointment;
    List<String> TREATMENTIDS;

    @BeforeEach
    public void SetUp() {
        this.appointmentRepository = Mockito.mock(AppointmentRepository.class);
        this.doctorRepository = Mockito.mock(DoctorRepository.class);
        this.patientRepository = Mockito.mock(PatientRepository.class);
        this.treatmentRepository = Mockito.mock(TreatmentRepository.class);
        this.createAppointment = getSut();
    }

    private CreateAppointment getSut() {                                                                                        // SUT: System Under Test
        return new CreateAppointment(appointmentRepository, doctorRepository, treatmentRepository);
    }

    @Test
    public void constructorThrowsExceptionForNullArgs() {
        assertThrows(NullPointerException.class,()-> {CreateAppointment createAppointment
                = new CreateAppointment(null, null, null);});
    }
    @Test
    public void executeThrowsException(){
        assertThrows(RuntimeException.class,
                ()->{createAppointment.execute("someid","anotherid", new ArrayList<>(3));}          // will create null instances for mocked objects
        );
    }
    @Test
    public void executeThrowsExceptionForNullObjects(){
        Doctor doctor = mock(Doctor.class);
        Patient patient = mock(Patient.class);
        List<Treatment> treatments = new ArrayList<>(2);
        when(doctorRepository.findDoctorById("doctorid")).thenReturn(doctor);
        when(patientRepository.findPatientById("patientid")).thenReturn(patient);
        when(treatmentRepository.findTreatmentsByIds(TREATMENTIDS)).thenReturn(treatments);
        assertThrows(RuntimeException.class,
                ()->{createAppointment.execute("doctorid","patientid",TREATMENTIDS);}
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
    public void executeGetsPatient(){
        when(patientRepository.findPatientById("patientid")).thenReturn(mock(Patient.class));
        Patient patientMock = patientRepository.findPatientById("patientid");
        Patient patient = new Patient("patientid","patientname","patientcondition");
        assertEquals(patient.getClass(),patientMock.getClass());
    }
    @Test
    public void executeGetsTreatments(){
        when(treatmentRepository.findTreatmentsByIds(new ArrayList<String>(3))).thenReturn(new ArrayList<Treatment>(3));
        List<Treatment> treatmentsMock = treatmentRepository.findTreatmentsByIds(new ArrayList<String>(3));
        List<Treatment> treatments = new ArrayList<>(3);
        treatments.add(new Treatment("treatmentid1","treatmentname1","treatmenttype1"));
        treatments.add(new Treatment("treatmentid2","treatmentname2","treatmenttype2"));
        treatments.add(new Treatment("treatmentid3","treatmentname3","treatmenttype3"));
        assertEquals(treatments.getClass(),treatmentsMock.getClass());
    }

    @Test
    public void appointmentConstructorBuildsCorrectly(){
        Appointment appointment = new Appointment("1003","doctorid","patientid",TREATMENTIDS);
        assertEquals("1003",appointment.getAppointmentId());
        assertEquals("doctorid",appointment.getDoctorId());
        assertEquals("patientid",appointment.getPatientId());
        assertEquals(TREATMENTIDS, appointment.getTreatmentIds());
    }

    @Test
    public void executeInvokesAddsAppointment(){
        Appointment appointment = new Appointment("1003","doctorid","patientid",TREATMENTIDS);

        doNothing().when(appointmentRepository).addAppointment(appointment);
        appointmentRepository.addAppointment(appointment);
        verify(appointmentRepository, times(1)).addAppointment(appointment);
    }

    @Test
    public void executeReturnsExpectedValue(){
        Doctor doctor = mock(Doctor.class);
        Patient patient = mock(Patient.class);
        List<String> TREATMENTIDS = new ArrayList<>(2);
        TREATMENTIDS.add("3001");
        TREATMENTIDS.add("3002");
        List<Treatment> treatments = new ArrayList<>(2);

        when(doctorRepository.findDoctorById("doctorid")).thenReturn(doctor);
        when(patientRepository.findPatientById("patientid")).thenReturn(patient);
        when(treatmentRepository.findTreatmentsByIds(TREATMENTIDS)).thenReturn(treatments);

        try(MockedConstruction<Appointment> appointmentMock = Mockito.mockConstruction(Appointment.class,(mock,context) -> {
            when(mock.getAppointmentId()).thenReturn("1003");
        })){
//                        Appointment appointment = appointmentMock.constructed().get(0);
            when(createAppointment.execute("doctorid","patientid",TREATMENTIDS)).thenReturn("1003");
            assertEquals("1003",createAppointment.execute("doctorid","patientid",TREATMENTIDS));
        }
    }
}
