package ru.javabegin.semyon.lastProject.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.javabegin.semyon.lastProject.dto.SensorDTO;
import ru.javabegin.semyon.lastProject.models.SensorModel;
import ru.javabegin.semyon.lastProject.services.SensorService;
import ru.javabegin.semyon.lastProject.validations.SensorValidator;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SensorController.class)
public class SensorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SensorService sensorService;

    @MockBean
    private SensorValidator sensorValidator;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testSensorRegistration() throws Exception {
        SensorDTO dto = new SensorDTO();
        dto.setName("TestSensor");

        Mockito.when(sensorValidator.validate(any(), any())).thenReturn(true);

        mockMvc.perform(post("/sensors/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());

        Mockito.verify(sensorService).saveSensor(any(SensorModel.class));
    }

    @Test
    public void testSensorRegistrationWithInvalidData() throws Exception {
        SensorDTO dto = new SensorDTO();
        dto.setName("");

        Mockito.when(sensorValidator.validate(any(), any())).thenReturn(false);

        mockMvc.perform(post("/sensors/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());

        Mockito.verify(sensorService, never()).saveSensor(any(SensorModel.class));
    }
} 