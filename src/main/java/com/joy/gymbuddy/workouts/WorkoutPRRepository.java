package com.joy.gymbuddy.workouts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutPRRepository extends JpaRepository<WorkoutPR,Integer> {
    WorkoutPR findByName(String name);
    List<WorkoutPR> findAllByProfileId(int profileId);
}
