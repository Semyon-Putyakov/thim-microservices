package ru.javabegin.semyon.lastProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.javabegin.semyon.lastProject.models.MeasurementsModel;

import java.util.List;

@Repository
public interface MeasurementsRepository extends JpaRepository<MeasurementsModel,Integer> {
    @Query("SELECT COUNT(DISTINCT m.date) FROM MeasurementsModel m WHERE m.raining = true")
    int countRainingDate();
}
