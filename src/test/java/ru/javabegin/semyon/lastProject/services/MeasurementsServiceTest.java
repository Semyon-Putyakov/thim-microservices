package ru.javabegin.semyon.lastProject.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.javabegin.semyon.lastProject.models.MeasurementsModel;
import ru.javabegin.semyon.lastProject.models.SensorModel;
import ru.javabegin.semyon.lastProject.repositories.MeasurementsRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MeasurementsServiceTest {

    @Mock
    private MeasurementsRepository measurementsRepository;

    @InjectMocks
    private MeasurementsService measurementsService;

    @Test
    public void testGetMeasurements() {
        List<MeasurementsModel> expectedMeasurements = Arrays.asList(
            createTestMeasurement(1L, 20.5, true),
            createTestMeasurement(2L, 25.0, false)
        );

        Mockito.when(measurementsRepository.findAll()).thenReturn(expectedMeasurements);

        List<MeasurementsModel> actualMeasurements = measurementsService.getMeasurements();

        Assertions.assertEquals(expectedMeasurements, actualMeasurements);
        Mockito.verify(measurementsRepository).findAll();
    }

    @Test
    public void testGetMeasurementsWhereRainingTrue() {
        List<MeasurementsModel> allMeasurements = Arrays.asList(
            createTestMeasurement(1L, 20.5, true),
            createTestMeasurement(2L, 25.0, true),
            createTestMeasurement(3L, 30.0, false)
        );

        Mockito.when(measurementsRepository.findAll()).thenReturn(allMeasurements);

        List<MeasurementsModel> rainyMeasurements = measurementsService.getMeasurementsWhereRainingTrue();

        Assertions.assertEquals(2, rainyMeasurements.size());
        Assertions.assertTrue(rainyMeasurements.stream().allMatch(MeasurementsModel::isRaining));
        Mockito.verify(measurementsRepository).findAll();
    }

    @Test
    public void testSave() {
        MeasurementsModel measurement = createTestMeasurement(1L, 20.5, true);

        measurementsService.save(measurement);

        Mockito.verify(measurementsRepository).save(measurement);
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