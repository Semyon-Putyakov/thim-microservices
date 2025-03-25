package ru.javabegin.semyon.lastProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javabegin.semyon.lastProject.models.MeasurementsModel;
import ru.javabegin.semyon.lastProject.models.SensorModel;
import ru.javabegin.semyon.lastProject.repositories.SensorRepository;

import java.util.Collection;
import java.util.Collections;


@Service
@Transactional
public class SensorService {
    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public void saveSensor(SensorModel sensorModel) {
        sensorRepository.save(sensorModel);
    }

    @Transactional(readOnly = true)
    public SensorModel findSensorByName(String name) {
        return sensorRepository.findByName(name);
    }
}
