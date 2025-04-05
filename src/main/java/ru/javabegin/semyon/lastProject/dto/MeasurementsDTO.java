package ru.javabegin.semyon.lastProject.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class MeasurementsDTO {

    private SensorDTO sensor;

    @NotNull(message = "Поле пустое")
    @DecimalMin(value = "-100.0", message = "Слишком низкая температура")
    @DecimalMax(value = "100.0", message = "Слишком высокая температура")
    @Column(name = "value")
    private float value;

    @NotNull(message = "Поле пустое")
    @Column(name = "raining")
    private boolean raining;

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }
}
