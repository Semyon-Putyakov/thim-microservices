package ru.javabegin.semyon.lastProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javabegin.semyon.lastProject.models.MeasurementsModel;
import ru.javabegin.semyon.lastProject.models.SensorModel;
import ru.javabegin.semyon.lastProject.repositories.MeasurementsRepository;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class MeasurementsService {

    private final MeasurementsRepository measurementsRepository;
    private final SensorService sensorService;

    @Autowired
    public MeasurementsService(MeasurementsRepository measurementsRepository, SensorService sensorService) {
        this.measurementsRepository = measurementsRepository;
        this.sensorService = sensorService;
    }

    public void save(MeasurementsModel measurementsModel) {
        SensorModel sensorModel = sensorService.findSensorByName(measurementsModel.getSensor().getName());
        measurementsModel.setSensor(sensorModel);
        measurementsModel.setDate(new Date());
        measurementsRepository.save(measurementsModel);
    }

    @Transactional(readOnly = true)
    public List<MeasurementsModel> getMeasurements() {
        return measurementsRepository.findAll();
    }

    @Transactional(readOnly = true)
    public void getMeasurementsWhereRainingTrue() {
        System.out.println(measurementsRepository.countRainingDate());
    }
}
