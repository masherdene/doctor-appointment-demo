package org.example.usecases;

import org.example.model.Appointment;
import org.example.model.Doctor;
import org.example.model.Patient;
import org.example.model.Treatment;
import org.example.repository.AppointmentRepository;
import org.example.repository.DoctorRepository;
import org.example.repository.PatientRepository;
import org.example.repository.TreatmentRepository;
import org.example.usecases.exception.UseCaseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedConstruction;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;


@ExtendWith(MockitoExtension.class)
public class CreateAppointmentTest {
    private AppointmentRepository appointmentRepository;                                                                // Depended On Components (DOTs)
    private DoctorRepository doctorRepository;
    private PatientRepository patientRepository;
    private TreatmentRepository treatmentRepository;
    private Doctor doctor;
    private Patient patient;
    private CreateAppointment createAppointment;                                                                        // System Under Test (SUT)
    private final LocalDateTime APPOINTMENTDATETIME = LocalDateTime.of(2023, Month.JUNE,30,15,30);
    private List<Treatment> TREATMENTS = new ArrayList<>(2);
    private List<String> TREATMENTIDS = new ArrayList<>(2);

    @BeforeEach
    public void SetUp() {
        this.appointmentRepository = mock(AppointmentRepository.class);
        this.doctorRepository = mock(DoctorRepository.class);
        this.patientRepository = mock(PatientRepository.class);
        this.treatmentRepository = mock(TreatmentRepository.class);
        this.doctor = mock(Doctor.class);
        this.patient = mock(Patient.class);
        this.createAppointment = getSut();
        TREATMENTIDS.add("3001");
        TREATMENTIDS.add("3002");
        TREATMENTS.add(new Treatment("treatmentid1","treatmentname1","treatmenttype1"));
        TREATMENTS.add(new Treatment("treatmentid2","treatmentname2","treatmenttype2"));
    }

    private CreateAppointment getSut() {                                                                                        // SUT: System Under Test
        return new CreateAppointment(appointmentRepository, doctorRepository, patientRepository, treatmentRepository);
    }

    @Test
    public void constructorThrowsExceptionForNullArgs() {
            assertThrows(NullPointerException.class,()-> {CreateAppointment createAppointment
                = new CreateAppointment(null, null, null, null);});
    }

    @Test
    public void executeThrowsException(){
        assertThrows(UseCaseException.class,
                ()->{createAppointment.execute(APPOINTMENTDATETIME,"patientid" ,"doctorId", new ArrayList<>(3));}          // will create null instances for mocked objects
        );
    }

    @Test
    public void executeThrowsExceptionForNullObjects(){
        when(doctorRepository.findDoctorById("doctorid")).thenReturn(null);
        when(patientRepository.findPatientById("patientid")).thenReturn(patient);
        when(treatmentRepository.findTreatmentsByIds(TREATMENTIDS)).thenReturn(TREATMENTS);
        assertThrows(UseCaseException.class,
                ()->{createAppointment.execute(APPOINTMENTDATETIME,"patientid","doctorid",TREATMENTIDS);}
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
        when(treatmentRepository.findTreatmentsByIds(new ArrayList<String>(2))).thenReturn(new ArrayList<Treatment>(2));
        List<Treatment> treatmentsMock = treatmentRepository.findTreatmentsByIds(new ArrayList<String>(2));
        assertEquals(TREATMENTS.getClass(),treatmentsMock.getClass());
    }

    @Test
    public void appointmentConstructorBuildsCorrectly(){
        Appointment appointment = new Appointment("appointmentid", APPOINTMENTDATETIME,"doctorid","patientid",TREATMENTIDS);
        assertEquals("appointmentid",appointment.getAppointmentId());
        assertEquals("doctorid",appointment.getDoctorId());
        assertEquals("patientid",appointment.getPatientId());
        assertEquals(TREATMENTIDS, appointment.getTreatmentIds());
    }

    @Test
    public void executeInvokesAddsAppointment(){
        Appointment appointment = new Appointment("appointmentid", APPOINTMENTDATETIME,"doctorid","patientid",TREATMENTIDS);
        doNothing().when(appointmentRepository).addAppointment(appointment);
        appointmentRepository.addAppointment(appointment);
        verify(appointmentRepository, times(1)).addAppointment(appointment);
    }

    @Test
    public void executeReturnsExpectedValue() throws UseCaseException {
        when(doctorRepository.findDoctorById("doctorid")).thenReturn(doctor);
        when(patientRepository.findPatientById("patientid")).thenReturn(patient);
        when(treatmentRepository.findTreatmentsByIds(TREATMENTIDS)).thenReturn(new ArrayList<Treatment>(2));

        try(MockedConstruction<Appointment> appointmentMock = Mockito.mockConstruction(Appointment.class,
            (mock,context) -> {
                when(mock.getAppointmentId()).thenReturn("appointmentid");
                when(mock.getAppointmentDateTime()).thenReturn(APPOINTMENTDATETIME);
        })){
            assertEquals(List.of("appointmentid","2023-06-30 15:30"),createAppointment.execute(APPOINTMENTDATETIME,"patientid","doctorid",TREATMENTIDS));
        }
    }

}
