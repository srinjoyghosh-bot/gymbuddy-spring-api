package com.joy.gymbuddy.meals.repo;

import com.joy.gymbuddy.meals.models.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<Meal,Integer> {
    List<Meal> findAllByProfileId(Integer profile_id);

    List<Meal> findAllByCreatedAtAndProfileId(LocalDate createdAt, Integer profile_id);
}
