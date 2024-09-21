package com.joy.gymbuddy.workouts;

import jakarta.persistence.ElementCollection;

import java.util.List;

public record WorkoutDTO(String name,

                         List<String> primaryMuscles,

                         List<String> secondaryMuscles,

                         List<String> instructions,
                         String category,

                         List<Repetition> reps) {
}
