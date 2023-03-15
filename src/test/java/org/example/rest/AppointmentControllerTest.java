package org.example.rest;

import org.example.repository.AppointmentRepository;
import org.example.repository.DoctorRepository;
import org.example.repository.PatientRepository;
import org.example.repository.TreatmentRepository;
import static org.example.rest.AppointmentController.CUSTOM_FORMATTER;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.example.usecases.CreateAppointment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AppointmentController.class})                                                          // @ContextConfiguration annotation includes a reference to the real controller we want to test: Spring will create a bean for this controller and will autowire it to the test.
@Import(AppointmentControllerTest.Config.class)
public class AppointmentControllerTest {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private TreatmentRepository treatmentRepository;
    @Autowired
    private CreateAppointment createAppointment;                                                                        // Depended On Component (DOT)
    @Autowired
    private AppointmentController appointmentController;                                                                // System Under Test (SUT) which is not mocked in the inner class "Config"

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private String APPOINTMENTDATE;

    // Inner configuration where we declare mock beans for the dependencies of the controller: Spring will inject this mock instances to our real controller and these dependencies will also be autowired to our test.
    @TestConfiguration
    protected static class Config {

        @Bean
        public CreateAppointment createAppointment() {
            return Mockito.mock(CreateAppointment.class);
        }
        @Bean
        public AppointmentRepository appointmentRepository() {
            return Mockito.mock(AppointmentRepository.class);
        }
        @Bean
        public DoctorRepository doctorRepository() {
            return Mockito.mock(DoctorRepository.class);
        }
        @Bean
        public PatientRepository patientRepository() {
            return Mockito.mock(PatientRepository.class);
        }
        @Bean
        public TreatmentRepository treatmentRepository() {
            return Mockito.mock(TreatmentRepository.class);
        }
    }

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this).close();
        objectMapper = JsonMapper.builder().findAndAddModules().build();
        this.appointmentController = getSut();
        mockMvc = MockMvcBuilders.standaloneSetup(appointmentController).build();
        APPOINTMENTDATE = "2023-06-30 15:30";
            }

    private AppointmentController getSut() {                                                                            // System Under Test (SUT)
        return new AppointmentController(appointmentRepository, doctorRepository, patientRepository, treatmentRepository);
    }

    // TEST 0: needs to temporarily declare existsCheck() public
/*    @Test
    public void existsCheckTest() throws Exception {
        doNothing().when(createAppointment).existsCheck(anyString(),anyString(),anyList());
        createAppointment.existsCheck("80001","1002",new ArrayList<>(List.of("0010","0011")));
        verify(createAppointment,times(1)).existsCheck("80001","1002",new ArrayList<>(List.of("0010","0011")));
    }*/

    // It appears that existsCheck() inside execute() is called: (1) needs to temporarily comment out existsCheck() in execute() or (2) use JUnit4 with PowerMock to mock the private method or (3) refactor the private method into a class implementing an interface that will be injected into 'CreateAppointment'
    @Test
    public void postPassengerTest() throws Exception {
        when(createAppointment.execute(LocalDateTime.parse(APPOINTMENTDATE,CUSTOM_FORMATTER),"patientid","doctorid", new ArrayList<>(List.of("0010","0011")))).thenReturn(List.of("1",APPOINTMENTDATE));
        String appointmentRestDto = objectMapper.writeValueAsString(new AppointmentRestDto("patientid","periodontitis","doctorid","periodontitis",new ArrayList<>(List.of("0010","0011")),LocalDateTime.parse(APPOINTMENTDATE, CUSTOM_FORMATTER)));
        mockMvc.perform(post("/appointments").accept(MediaType.APPLICATION_JSON).characterEncoding("utf-8").contentType(MediaType.APPLICATION_JSON).content(appointmentRestDto))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.appointmentId").value(null))
                .andExpect(jsonPath("$.appointmentDateTime").value(LocalDateTime.parse(APPOINTMENTDATE, CUSTOM_FORMATTER)));
//                .andExpect(jsonPath("$.doctorId").value("doctorid"))
//                .andExpect(jsonPath("$.doctorId").value("patientid"));
//        Mockito.verify(appointmentRepository,times(1)).addAppointment(appointment);
    }

}