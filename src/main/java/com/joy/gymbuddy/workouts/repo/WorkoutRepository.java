package com.joy.gymbuddy.workouts.repo;

import com.joy.gymbuddy.workouts.model.entities.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout,Integer> {
    List<Workout> findAllByProfileId(Integer profileId);
    List<Workout> findAllByUpdatedAtAndProfileId(LocalDate createdAt, Integer profileId);
}
