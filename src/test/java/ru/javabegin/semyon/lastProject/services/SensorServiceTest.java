package ru.javabegin.semyon.lastProject.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.javabegin.semyon.lastProject.models.SensorModel;
import ru.javabegin.semyon.lastProject.repositories.SensorRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SensorServiceTest {

    @Mock
    private SensorRepository sensorRepository;

    @InjectMocks
    private SensorService sensorService;

    @Test
    public void testSaveSensor() {
        SensorModel sensor = createTestSensor(1L, "TestSensor");

        sensorService.saveSensor(sensor);

        Mockito.verify(sensorRepository).save(sensor);
    }

    @Test
    public void testGetAllSensors() {
        List<SensorModel> expectedSensors = Arrays.asList(
            createTestSensor(1L, "Sensor1"),
            createTestSensor(2L, "Sensor2")
        );

        Mockito.when(sensorRepository.findAll()).thenReturn(expectedSensors);

        List<SensorModel> actualSensors = sensorService.getAllSensors();

        Assertions.assertEquals(expectedSensors, actualSensors);
        Mockito.verify(sensorRepository).findAll();
    }

    @Test
    public void testGetSensorByName() {
        String sensorName = "TestSensor";
        SensorModel expectedSensor = createTestSensor(1L, sensorName);

        Mockito.when(sensorRepository.findByName(sensorName)).thenReturn(expectedSensor);

        SensorModel actualSensor = sensorService.getSensorByName(sensorName);

        Assertions.assertEquals(expectedSensor, actualSensor);
        Mockito.verify(sensorRepository).findByName(sensorName);
    }

    private SensorModel createTestSensor(Long id, String name) {
        SensorModel sensor = new SensorModel();
        sensor.setId(id);
        sensor.setName(name);
        return sensor;
    }
} 