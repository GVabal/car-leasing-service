package dev.vabalas.carleasing.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import dev.vabalas.carleasing.entity.Application;
import dev.vabalas.carleasing.entity.Car;
import dev.vabalas.carleasing.entity.Person;
import dev.vabalas.carleasing.service.ApplicationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class ApplicationControllerTest {

    private static final String URL = "/carleasing";
    private static final int APPLICATION_ID = 999;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private ApplicationService serviceMock;

    @Test
    void getOne_returnsFoundApplication() {
        Car car = new Car("make", "model", 1999, Car.FuelType.OTHER, Car.Damage.OTHER);
        Person person1 = new Person("firstName", "lastName", "socnr123", 0, 600);
        Application applicationMock = new Application(car, person1, 10000);
        applicationMock.setId(APPLICATION_ID);
        applicationMock.setApproved(false);

        Mockito.when(serviceMock.getApplicationById(APPLICATION_ID)).thenReturn(applicationMock);

        ResponseEntity<Application> response = restTemplate.getForEntity(URL + "/application/" + APPLICATION_ID, Application.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().getId(), APPLICATION_ID);
        assertEquals(response.getBody().isApproved(), false);
        assertEquals(response.getBody().getPerson().getMonthlyIncome(), 600);
        assertEquals(response.getBody().getCar().getMake(), "make");
        assertEquals(response.getBody().getCreditAmount(), 10000);
    }

    @Test
    void apply_returnsProcessedApplication() throws Exception {
        ApplicationDto applicationDto = new ApplicationDto(1, "make", "model", 1999,
                "other", "other", "firstName", "lastName",
                "socnr1234", 1, 1000);
        Car car = new Car("make", "model", 1999, Car.FuelType.OTHER, Car.Damage.OTHER);
        Person person1 = new Person("firstName", "lastName", "socnr123", 0, 600);
        Application applicationMock = new Application(car, person1, 10000);
        applicationMock.setId(APPLICATION_ID);
        applicationMock.setApproved(false);

        MockitoAnnotations.initMocks(this);
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        Mockito.when(serviceMock.apply(Mockito.any(applicationDto.getClass()))).thenReturn(applicationMock);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter writer = mapper.writer().withDefaultPrettyPrinter();
        String applicationDtoJson = writer.writeValueAsString(applicationDto);

        mockMvc.perform(MockMvcRequestBuilders.post(URL + "/apply")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(applicationDtoJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(APPLICATION_ID))
                .andExpect(MockMvcResultMatchers.jsonPath("$.approved").value(false));
    }
}