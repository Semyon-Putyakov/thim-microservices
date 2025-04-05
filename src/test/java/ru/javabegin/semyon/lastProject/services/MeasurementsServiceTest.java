package ru.javabegin.semyon.lastProject.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.javabegin.semyon.lastProject.models.MeasurementsModel;
import ru.javabegin.semyon.lastProject.models.SensorModel;
import ru.javabegin.semyon.lastProject.repositories.MeasurementsRepository;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MeasurementsServiceTest {

    @Mock
    private MeasurementsRepository measurementsRepository;

    @Mock
    private SensorService sensorService;

    @InjectMocks
    private MeasurementsService measurementsService;

    @Test
    void save_ShouldSetSensorAndDateAndCallRepositorySave() {
        SensorModel sensor = new SensorModel();
        sensor.setName("Test Sensor");
        MeasurementsModel measurement = new MeasurementsModel();
        measurement.setSensor(sensor);
        Mockito.when(sensorService.findSensorByName(sensor.getName())).thenReturn(sensor);

        measurementsService.save(measurement);

        Assertions.assertNotNull(measurement.getDate());
        Assertions.assertEquals(sensor, measurement.getSensor());
        Mockito.verify(measurementsRepository, times(1)).save(measurement);
        Mockito.verify(sensorService, times(1)).findSensorByName(sensor.getName());
    }

    @Test
    void getMeasurements_ShouldReturnAllMeasurements() {
        MeasurementsModel m1 = new MeasurementsModel();
        MeasurementsModel m2 = new MeasurementsModel();
        List<MeasurementsModel> expectedMeasurements = Arrays.asList(m1, m2);
        Mockito.when(measurementsRepository.findAll()).thenReturn(expectedMeasurements);

        List<MeasurementsModel> result = measurementsService.getMeasurements();

        Assertions.assertEquals(2, result.size());
        Mockito.verify(measurementsRepository, times(1)).findAll();
    }

    @Test
    void getMeasurementsWhereRainingTrue_ShouldCallRepositoryMethod() {
        measurementsService.getMeasurementsWhereRainingTrue();

        Mockito.verify(measurementsRepository, times(1)).countRainingDate();
    }
}