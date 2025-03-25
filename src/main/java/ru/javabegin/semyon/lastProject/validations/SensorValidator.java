package ru.javabegin.semyon.lastProject.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.javabegin.semyon.lastProject.models.SensorModel;
import ru.javabegin.semyon.lastProject.services.SensorService;

@Component
public class SensorValidator implements Validator {

    private final SensorService sensorService;

    @Autowired
    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return SensorModel.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SensorModel sensor = (SensorModel) target;
        if(sensorService.findSensorByName(sensor.getName()) != null){
            errors.rejectValue("name", "Такой датчик уже существует");
        }
    }
}
