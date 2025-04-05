package ru.javabegin.semyon.lastProject.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.javabegin.semyon.lastProject.dto.MeasurementsDTO;
import ru.javabegin.semyon.lastProject.models.MeasurementsModel;
import ru.javabegin.semyon.lastProject.services.MeasurementsService;
import ru.javabegin.semyon.lastProject.validations.MeasurementsValidation;

import java.util.List;

import static org.hamcrest.Matchers.*;
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
    private ModelMapper modelMapper;

    @MockBean
    private MeasurementsValidation measurementsValidation;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetMeasurements() throws Exception {
        MeasurementsModel model = new MeasurementsModel();
        MeasurementsDTO dto = new MeasurementsDTO();
        Mockito.when(measurementsService.getMeasurements()).thenReturn(List.of(model));
        Mockito.when(modelMapper.map(model, MeasurementsDTO.class)).thenReturn(dto);

        mockMvc.perform(get("/measurements"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void testGetRainyDaysCount() throws Exception {
        mockMvc.perform(get("/measurements/rainyDaysCount"))
                .andExpect(status().isOk());

        Mockito.verify(measurementsService, times(1)).getMeasurementsWhereRainingTrue();
    }

    @Test
    void testAddMeasurements_valid() throws Exception {
        MeasurementsDTO dto = new MeasurementsDTO();
        MeasurementsModel model = new MeasurementsModel();

        Mockito.when(modelMapper.map(Mockito.any(MeasurementsDTO.class), eq(MeasurementsModel.class))).thenReturn(model);

        mockMvc.perform(post("/measurements/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }
}
