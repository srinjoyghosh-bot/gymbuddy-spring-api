package com.joy.gymbuddy.workouts.dto;

import com.joy.gymbuddy.workouts.model.Repetition;

import java.util.List;

public record WorkoutDTO(String name,

                         List<String> primaryMuscles,

                         List<String> secondaryMuscles,

                         List<String> instructions,
                         String category,

                         List<Repetition> reps) {
}
