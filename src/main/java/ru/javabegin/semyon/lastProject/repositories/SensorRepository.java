package ru.javabegin.semyon.lastProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.javabegin.semyon.lastProject.models.SensorModel;

@Repository
public interface SensorRepository extends JpaRepository<SensorModel, Integer> {
    SensorModel findByName(String name);
}
