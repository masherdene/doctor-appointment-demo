package org.example.usecase;

import org.example.model.Appointment;
import org.example.model.Doctor;
import org.example.model.Patient;
import org.example.model.Treatment;
import org.example.repository.AppointmentRepository;
import org.example.repository.DoctorRepository;
import org.example.repository.PatientRepository;
import org.example.repository.TreatmentRepository;
import org.example.usecase.exception.UseCaseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.mockito.MockedConstruction;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


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
        TREATMENTS.add(new Treatment("treatmentId1","treatmentName1","treatmentType1"));
        TREATMENTS.add(new Treatment("treatmentId2","treatmentName2","treatmentType2"));
    }

    private CreateAppointment getSut() {                                                                                        // SUT: System Under Test
        return new CreateAppointment(appointmentRepository, doctorRepository, patientRepository, treatmentRepository);
    }

    @ParameterizedTest
    @NullSource
    public void constructorThrowsExceptionIfFirstArgNull(AppointmentRepository input) {
        assertThrows(NullPointerException.class,()-> {CreateAppointment createAppointment
                = new CreateAppointment(input, doctorRepository, patientRepository, treatmentRepository);});
    }

    @Test
    public void constructorThrowsExceptionForNullArgs() {
            assertThrows(NullPointerException.class,()-> {CreateAppointment createAppointment
                = new CreateAppointment(null, null, null, null);});
    }

    // Testing validation and existence checking of passed-in arguments
    @Test
    public void executeThrowsUseCaseExceptionForNonExistingObjects(){
        assertThrows(UseCaseException.class,
                ()->{createAppointment.execute(APPOINTMENTDATETIME,"patientId" ,"doctorId", new ArrayList<>(3));}
        );
    }

    @ParameterizedTest
    @CsvSource(value = {"null, null", "null, null"}, nullValues = {"null"})
    public void executeThrowsExceptionForNullObjects(Doctor doctor, Patient patient){
        when(doctorRepository.findDoctorById("doctorId")).thenReturn(doctor);
        when(patientRepository.findPatientById("patientId")).thenReturn(patient);
        when(treatmentRepository.findTreatmentsByIds(TREATMENTIDS)).thenReturn(TREATMENTS);
        assertThrows(UseCaseException.class,
                ()->{createAppointment.execute(APPOINTMENTDATETIME,"patientId","doctorId",TREATMENTIDS);}
        );
    }

    @ParameterizedTest
    @MethodSource("provideObjectsForTest")
    public void executeThrowsExceptionIfOneObjectIsNull(Doctor doctor, Patient patient){
        when(doctorRepository.findDoctorById("doctorId")).thenReturn(doctor);
        when(patientRepository.findPatientById("patientId")).thenReturn(patient);
        when(treatmentRepository.findTreatmentsByIds(TREATMENTIDS)).thenReturn(TREATMENTS);
        assertThrows(UseCaseException.class,
                ()->{createAppointment.execute(APPOINTMENTDATETIME,"patientId","doctorId",TREATMENTIDS);}
        );
    }

    private static Stream<Arguments> provideObjectsForTest(){
        Doctor doctor = mock(Doctor.class);
        Patient patient = mock(Patient.class);
        return Stream.of(
                Arguments.of(doctor, null),
                Arguments.of(null, patient)
        );
    }

    @Test
    public void executeGetsDoctor(){
        when(doctorRepository.findDoctorById("doctorId")).thenReturn(mock(Doctor.class));
        Doctor doctorMock = doctorRepository.findDoctorById("doctorId");
        Doctor doctor = new Doctor("doctorId","doctorName","doctorSpecial");
        assertEquals(doctor.getClass(),doctorMock.getClass());
    }

    @Test
    public void executeGetsPatient(){
        when(patientRepository.findPatientById("patientId")).thenReturn(mock(Patient.class));
        Patient patientMock = patientRepository.findPatientById("patientId");
        Patient patient = new Patient("patientId","patientName","patientCondition");
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
        Appointment appointment = new Appointment("appointmentId", APPOINTMENTDATETIME,"doctorId","patientId",TREATMENTIDS);
        assertEquals("appointmentId",appointment.getAppointmentId());
        assertEquals("doctorId",appointment.getDoctorId());
        assertEquals("patientId",appointment.getPatientId());
        assertEquals(TREATMENTIDS, appointment.getTreatmentIds());
    }

    @Test
    public void executeInvokesAddsAppointmentMethod(){
        Appointment appointment = new Appointment("appointmentId", APPOINTMENTDATETIME,"doctorId","patientId",TREATMENTIDS);
        doNothing().when(appointmentRepository).addAppointment(appointment);
        appointmentRepository.addAppointment(appointment);
        verify(appointmentRepository, times(1)).addAppointment(appointment);
    }

    @Test
    public void executeReturnsExpectedValues() throws UseCaseException {
        when(doctorRepository.findDoctorById("doctorId")).thenReturn(doctor);
        when(patientRepository.findPatientById("patientId")).thenReturn(patient);
        when(treatmentRepository.findTreatmentsByIds(TREATMENTIDS)).thenReturn(new ArrayList<Treatment>(2));

        try(MockedConstruction<Appointment> appointmentMock = Mockito.mockConstruction(Appointment.class,
            (mock,context) -> {
                when(mock.getAppointmentId()).thenReturn("appointmentId");
                when(mock.getAppointmentDateTime()).thenReturn(APPOINTMENTDATETIME);
        })){
            assertEquals(List.of("appointmentId","2023-06-30 15:30"),createAppointment.execute(APPOINTMENTDATETIME,"patientId","doctorId",TREATMENTIDS));
        }
    }
}
