package ru.javabegin.semyon.lastProject.validations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import ru.javabegin.semyon.lastProject.models.MeasurementsModel;
import ru.javabegin.semyon.lastProject.models.SensorModel;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MeasurementsValidationTest {

    @InjectMocks
    private MeasurementsValidation measurementsValidation;

    @Test
    public void testValidateWithValidData() {
        MeasurementsModel measurement = createTestMeasurement(20.5, true);
        BindingResult bindingResult = mock(BindingResult.class);

        measurementsValidation.validate(measurement, bindingResult);

        Mockito.verify(bindingResult, never()).addError(any(FieldError.class));
    }

    @Test
    public void testValidateWithInvalidValue() {
        MeasurementsModel measurement = createTestMeasurement(-100.0, true);
        BindingResult bindingResult = mock(BindingResult.class);

        measurementsValidation.validate(measurement, bindingResult);

        Mockito.verify(bindingResult).addError(any(FieldError.class));
    }

    @Test
    public void testValidateWithNullSensor() {
        MeasurementsModel measurement = createTestMeasurement(20.5, true);
        measurement.setSensor(null);
        BindingResult bindingResult = mock(BindingResult.class);

        measurementsValidation.validate(measurement, bindingResult);

        Mockito.verify(bindingResult).addError(any(FieldError.class));
    }

    private MeasurementsModel createTestMeasurement(double value, boolean raining) {
        MeasurementsModel measurement = new MeasurementsModel();
        measurement.setValue(value);
        measurement.setRaining(raining);
        SensorModel sensor = new SensorModel();
        sensor.setName("TestSensor");
        measurement.setSensor(sensor);
        return measurement;
    }
} 