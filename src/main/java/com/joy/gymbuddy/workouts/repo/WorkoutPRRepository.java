package com.joy.gymbuddy.workouts.repo;

import com.joy.gymbuddy.workouts.model.entities.WorkoutPR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutPRRepository extends JpaRepository<WorkoutPR,Integer> {
    WorkoutPR findByName(String name);
    List<WorkoutPR> findAllByProfileId(int profileId);
}
