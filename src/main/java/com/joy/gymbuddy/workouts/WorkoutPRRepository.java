package com.joy.gymbuddy.workouts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutPRRepository extends JpaRepository<WorkoutPR,Integer> {
    WorkoutPR findByName(String name);
}
