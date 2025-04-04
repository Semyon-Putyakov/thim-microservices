package ru.javabegin.semyon.lastProject.validations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import ru.javabegin.semyon.lastProject.models.SensorModel;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SensorValidatorTest {

    @InjectMocks
    private SensorValidator sensorValidator;

    @Test
    public void testValidateWithValidData() {
        SensorModel sensor = createTestSensor("TestSensor");
        BindingResult bindingResult = mock(BindingResult.class);

        sensorValidator.validate(sensor, bindingResult);

        Mockito.verify(bindingResult, never()).addError(any(FieldError.class));
    }

    @Test
    public void testValidateWithEmptyName() {
        SensorModel sensor = createTestSensor("");
        BindingResult bindingResult = mock(BindingResult.class);

        sensorValidator.validate(sensor, bindingResult);

        Mockito.verify(bindingResult).addError(any(FieldError.class));
    }

    @Test
    public void testValidateWithNullName() {
        SensorModel sensor = createTestSensor(null);
        BindingResult bindingResult = mock(BindingResult.class);

        sensorValidator.validate(sensor, bindingResult);

        Mockito.verify(bindingResult).addError(any(FieldError.class));
    }

    private SensorModel createTestSensor(String name) {
        SensorModel sensor = new SensorModel();
        sensor.setName(name);
        return sensor;
    }
} 