package ru.javabegin.semyon.lastProject.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "measurements")
public class MeasurementsModel {


    @ManyToOne
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    private SensorModel sensor;

    @NotNull(message = "Поле пустое")
    @DecimalMin(value = "-100.0" , message = "Слишком низкая температура")
    @DecimalMax(value = "100.0", message = "Слишком высокая температура")
    @Column(name = "value")
    private float value;

    @NotNull(message = "Поле пустое")
    @Column(name = "raining")
    private boolean raining;

    @Column(name = "date")
    private Date date;

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public SensorModel getSensor() {
        return sensor;
    }

    public void setSensor(SensorModel sensor) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MeasurementsModel{" +
                "sensor=" + sensor +
                ", value=" + value +
                ", raining=" + raining +
                ", date=" + date +
                ", id=" + id +
                '}';
    }
}