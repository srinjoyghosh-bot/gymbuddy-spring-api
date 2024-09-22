package com.joy.gymbuddy.workouts.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Repetition {
    private Integer reps;
    private Double resistance;
}
