package ru.javabegin.semyon.lastProject.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.javabegin.semyon.lastProject.models.MeasurementsModel;
import ru.javabegin.semyon.lastProject.services.SensorService;

@Component
public class MeasurementsValidation implements Validator {

    private final SensorService sensorService;

    @Autowired
    public MeasurementsValidation(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return MeasurementsModel.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MeasurementsModel measurementsModel = (MeasurementsModel) target;

        if(sensorService.findSensorByName(measurementsModel.getSensor().getName()) == null) {
            errors.rejectValue("sensor", "Такого сенсора не существует!");
        }
    }
}
