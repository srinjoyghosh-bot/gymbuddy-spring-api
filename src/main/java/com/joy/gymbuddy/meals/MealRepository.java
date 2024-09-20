package com.joy.gymbuddy.meals;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<Meal,Integer> {
    List<Meal> findAllByProfileId(Integer profile_id);
}
