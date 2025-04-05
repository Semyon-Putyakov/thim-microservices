package ru.javabegin.semyon.lastProject.services;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.javabegin.semyon.lastProject.models.SensorModel;
import ru.javabegin.semyon.lastProject.repositories.SensorRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SensorServiceTest {

    @Mock
    private SensorRepository sensorRepository;

    @InjectMocks
    private SensorService sensorService;

    @Test
    void saveSensor_ShouldCallRepositorySave() {
        SensorModel sensor = new SensorModel();
        sensor.setName("Test Sensor");

        sensorService.saveSensor(sensor);

        Mockito.verify(sensorRepository, times(1)).save(sensor);
    }

    @Test
    void findSensorByName_ShouldReturnSensor() {
        String sensorName = "Test Sensor";
        SensorModel expectedSensor = new SensorModel();
        expectedSensor.setName(sensorName);

        Mockito.when(sensorRepository.findByName(sensorName)).thenReturn(expectedSensor);
        SensorModel result = sensorService.findSensorByName(sensorName);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(sensorName, result.getName());
        Mockito.verify(sensorRepository, times(1)).findByName(sensorName);
    }
}