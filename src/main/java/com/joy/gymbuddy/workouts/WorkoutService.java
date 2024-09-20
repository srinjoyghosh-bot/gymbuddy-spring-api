package com.joy.gymbuddy.workouts;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class WorkoutService {
    private WorkoutRepository workoutRepository;
    private WorkoutPRRepository workoutPRRepository;

    public List<Workout> getAllWorkouts(Integer profileId) {
        return workoutRepository.findAllByProfileId(profileId);
    }

    public List<Workout> getAllWorkoutsByDate(Integer profileId, LocalDate date) {
        return workoutRepository.findAllByUpdatedAtAndProfileId(date, profileId);
    }

    public List<WorkoutPR> getAllWorkoutPRs(Integer profileId) {
        return workoutPRRepository.findAllByProfileId(profileId);
    }
}
