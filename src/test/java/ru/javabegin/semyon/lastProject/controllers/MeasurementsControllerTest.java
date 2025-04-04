package ru.javabegin.semyon.lastProject.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.javabegin.semyon.lastProject.dto.MeasurementsDTO;
import ru.javabegin.semyon.lastProject.models.MeasurementsModel;
import ru.javabegin.semyon.lastProject.models.SensorModel;
import ru.javabegin.semyon.lastProject.services.MeasurementsService;
import ru.javabegin.semyon.lastProject.validations.MeasurementsValidation;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MeasurementsController.class)
public class MeasurementsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MeasurementsService measurementsService;

    @MockBean
    private MeasurementsValidation measurementsValidation;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetMeasurements() throws Exception {
        List<MeasurementsModel> measurements = Arrays.asList(
            createTestMeasurement(1L, 20.5, true),
            createTestMeasurement(2L, 25.0, false)
        );

        Mockito.when(measurementsService.getMeasurements()).thenReturn(measurements);

        mockMvc.perform(get("/measurements"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].value").value(20.5))
                .andExpect(jsonPath("$[1].value").value(25.0));

        Mockito.verify(measurementsService).getMeasurements();
    }

    @Test
    public void testAddMeasurement() throws Exception {
        MeasurementsDTO dto = new MeasurementsDTO();
        dto.setValue(30.0);
        dto.setRaining(true);
        SensorModel sensor = new SensorModel();
        sensor.setName("TestSensor");
        dto.setSensor(sensor);

        Mockito.when(measurementsValidation.validate(any(), any())).thenReturn(true);

        mockMvc.perform(post("/measurements/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());

        Mockito.verify(measurementsService).save(any(MeasurementsModel.class));
    }

    @Test
    public void testGetRainyDaysCount() throws Exception {
        List<MeasurementsModel> rainyMeasurements = Arrays.asList(
            createTestMeasurement(1L, 20.5, true),
            createTestMeasurement(2L, 25.0, true)
        );

        Mockito.when(measurementsService.getMeasurementsWhereRainingTrue()).thenReturn(rainyMeasurements);

        mockMvc.perform(get("/measurements/rainyDaysCount"))
                .andExpect(status().isOk());

        Mockito.verify(measurementsService).getMeasurementsWhereRainingTrue();
    }

    private MeasurementsModel createTestMeasurement(Long id, double value, boolean raining) {
        MeasurementsModel measurement = new MeasurementsModel();
        measurement.setId(id);
        measurement.setValue(value);
        measurement.setRaining(raining);
        SensorModel sensor = new SensorModel();
        sensor.setName("TestSensor");
        measurement.setSensor(sensor);
        return measurement;
    }
} 