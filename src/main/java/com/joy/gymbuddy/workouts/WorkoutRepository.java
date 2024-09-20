package com.joy.gymbuddy.workouts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout,Integer> {
    public List<Workout> findAllByProfileId(Integer profileId);
}
