package org.example;

import org.example.repository.AppointmentRepository;
import org.example.repository.DoctorRepository;
import org.example.repository.PatientRepository;
import org.example.repository.TreatmentRepository;
import org.example.rest.AppointmentController;
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

import org.example.rest.AppointmentRestDto;
import org.example.usecases.CreateAppointment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith({MockitoExtension.class, SpringExtension.class})
@ContextConfiguration(classes = {AppointmentController.class})                                                          // @ContextConfiguration annotation includes a reference to the real controller we want to test: Spring will create a bean for this controller and will autowire it to the test.
@Import(AppointmentControllerTest.Config.class)
public class AppointmentControllerTest {

    @Mock
    private AppointmentRepository appointmentRepository;
    @Mock
    private DoctorRepository doctorRepository;
    @Mock
    private PatientRepository patientRepository;
    @Mock
    private TreatmentRepository treatmentRepository;
    @Autowired
    private CreateAppointment createAppointment;                                                                        // Depended on Component (DOT)
    @Autowired
    private AppointmentController appointmentController;                                                                // System Under Test (SUT)
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private String APPOINTMENTDATE;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this).close();
        objectMapper = JsonMapper.builder().findAndAddModules().build();
        mockMvc = MockMvcBuilders.standaloneSetup(appointmentController).build();
//        createAppointment = new CreateAppointment(appointmentRepository,doctorRepository,patientRepository,treatmentRepository);
        APPOINTMENTDATE = "2023-06-30 15:30";
    }

    // Inner configuration where we declare mock beans for the dependencies of the controller: Spring will inject this mock instances to our real controller and these dependencies will also be autowired to our test.
    @TestConfiguration
    protected static class Config {
        @Bean
        public CreateAppointment createAppointment() {
            return Mockito.mock(CreateAppointment.class);
        }
    }

    @Test
    public void postPassengerTest() throws Exception {
        when(createAppointment.execute(LocalDateTime.parse(APPOINTMENTDATE,CUSTOM_FORMATTER),"80001","1002",new ArrayList<>(List.of("0010","0011")))).thenReturn(List.of("1",APPOINTMENTDATE));
        String appointmentRestDto = objectMapper.writeValueAsString(new AppointmentRestDto("80001","periodontitis","1002","periodontitis",new ArrayList<>(List.of("0010","0011")),LocalDateTime.parse(APPOINTMENTDATE, CUSTOM_FORMATTER)));
        mockMvc.perform(post("/newappointment").accept(MediaType.APPLICATION_JSON).characterEncoding("utf-8").contentType(MediaType.APPLICATION_JSON).content(appointmentRestDto))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//                .andExpect(jsonPath("$.appointmentId").value("1"));
//                .andExpect(jsonPath("$.appointmentDate").value(LocalDateTime.parse(APPOINTMENTDATE, CUSTOM_FORMATTER)))
//                .andExpect(jsonPath("$.doctorId").value("doctorid"))
//                .andExpect(jsonPath("$.doctorId").value("patientid"));
//        Mockito.verify(appointmentRepository,times(1)).addAppointment(appointment);
    }

}