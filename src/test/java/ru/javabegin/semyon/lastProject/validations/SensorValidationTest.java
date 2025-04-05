package ru.javabegin.semyon.lastProject.validations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import ru.javabegin.semyon.lastProject.models.SensorModel;
import ru.javabegin.semyon.lastProject.services.SensorService;

@ExtendWith(MockitoExtension.class)
public class SensorValidationTest{

    @Mock
    private SensorService sensorService;

    @InjectMocks
    private SensorValidator sensorValidator;


    @Test
    void validate_ShouldAddErrorWhenSensorExists() {
        SensorModel sensor = new SensorModel();
        sensor.setName("Existing Sensor");

        Errors errors = new BeanPropertyBindingResult(sensor, "sensor");

        Mockito.when(sensorService.findSensorByName(sensor.getName())).thenReturn(sensor);

        sensorValidator.validate(sensor, errors);

        Assertions.assertTrue(errors.hasErrors());
    }

    @Test
    void validate_ShouldNotAddErrorWhenSensorDoesNotExist() {
        SensorModel sensor = new SensorModel();
        sensor.setName("New Sensor");

        Errors errors = new BeanPropertyBindingResult(sensor, "sensor");

        Mockito.when(sensorService.findSensorByName(sensor.getName())).thenReturn(null);

        sensorValidator.validate(sensor, errors);

        Assertions.assertFalse(errors.hasErrors());
    }
}