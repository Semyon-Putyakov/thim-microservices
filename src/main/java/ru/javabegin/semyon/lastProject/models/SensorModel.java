package ru.javabegin.semyon.lastProject.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;

@Entity
@Table(name = "sensors")
public class SensorModel {

    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @Column(name = "name")
    @Size(max = 30, message = "Максимальное количество символов 30, а минимальное 0")
    @NotEmpty(message = "Поле пустое")
    private String name;

    @OneToMany(mappedBy = "sensor")
    private List<MeasurementsModel> measurements;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<MeasurementsModel> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<MeasurementsModel> measurements) {
        this.measurements = measurements;
    }

    @Override
    public String toString() {
        return "SensorModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", measurements=" + measurements +
                '}';
    }
}
