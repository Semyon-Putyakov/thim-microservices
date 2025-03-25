package ru.javabegin.semyon.lastProject.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class SensorDTO {
    @Size(max = 30, message = "Максимальное количество символов 30, а минимальное 0")
    @NotEmpty
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
