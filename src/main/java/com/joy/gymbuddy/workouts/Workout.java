package com.joy.gymbuddy.workouts;

import com.joy.gymbuddy.BaseEntity;
import com.joy.gymbuddy.auth.models.Profile;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
public class Workout extends BaseEntity {

    private String name;
    @ElementCollection
    private List<String> primaryMuscles;
    @ElementCollection
    private List<String> secondaryMuscles;
    @ElementCollection
    private List<String> instructions;
    private String category;
    @ElementCollection
    private List<Repetition> reps;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    public Double getMaxRep() {
        return this.reps.stream().max(Comparator.comparingDouble(Repetition::getResistance)).map(Repetition::getResistance).orElseThrow(() -> new IllegalArgumentException("no reps found"));
    }
}

