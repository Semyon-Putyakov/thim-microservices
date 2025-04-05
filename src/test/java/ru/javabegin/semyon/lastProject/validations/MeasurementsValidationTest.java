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
import ru.javabegin.semyon.lastProject.models.MeasurementsModel;
import ru.javabegin.semyon.lastProject.models.SensorModel;
import ru.javabegin.semyon.lastProject.services.SensorService;


@ExtendWith(MockitoExtension.class)
class MeasurementsValidationTest {

    @Mock
    private SensorService sensorService;

    @InjectMocks
    private MeasurementsValidation measurementsValidation;


    @Test
    void validate_ShouldAddErrorWhenSensorDoesNotExist() {
        SensorModel sensor = new SensorModel();
        sensor.setName("Non-existent Sensor");

        MeasurementsModel measurement = new MeasurementsModel();
        measurement.setSensor(sensor);

        Errors errors = new BeanPropertyBindingResult(measurement, "measurement");

        Mockito.when(sensorService.findSensorByName(sensor.getName())).thenReturn(null);

        measurementsValidation.validate(measurement, errors);

        Assertions.assertTrue(errors.hasErrors());
    }

    @Test
    void validate_ShouldNotAddErrorWhenSensorExists() {
        SensorModel sensor = new SensorModel();
        sensor.setName("Existing Sensor");

        MeasurementsModel measurement = new MeasurementsModel();
        measurement.setSensor(sensor);

        Errors errors = new BeanPropertyBindingResult(measurement, "measurement");

        Mockito.when(sensorService.findSensorByName(sensor.getName())).thenReturn(sensor);

        measurementsValidation.validate(measurement, errors);

        Assertions.assertFalse(errors.hasErrors());
    }
}