package ru.javabegin.semyon.lastProject.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.javabegin.semyon.lastProject.dto.SensorDTO;
import ru.javabegin.semyon.lastProject.models.SensorModel;
import ru.javabegin.semyon.lastProject.services.SensorService;
import ru.javabegin.semyon.lastProject.validations.SensorValidator;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SensorController.class)
public class SensorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SensorService sensorService;

    @MockBean
    private SensorValidator sensorValidator;

    @Test
    public void registration_Success() throws Exception {
        SensorDTO sensorDTO = new SensorDTO();
        sensorDTO.setName("Test Sensor");

        SensorModel sensorModel = new SensorModel();
        sensorModel.setName("Test Sensor");

        Mockito.doNothing().when(sensorValidator).validate(Mockito.any(SensorModel.class), Mockito.any());
        Mockito.doNothing().when(sensorService).saveSensor(Mockito.any(SensorModel.class));

        mockMvc.perform(post("/sensors/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sensorDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void registration_ValidationError() throws Exception {
        SensorDTO sensorDTO = new SensorDTO();
        sensorDTO.setName("");

        mockMvc.perform(post("/sensors/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sensorDTO)))
                .andExpect(status().isBadRequest());
    }
}