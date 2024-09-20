package com.joy.gymbuddy.workouts;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.joy.gymbuddy.BaseEntity;
import com.joy.gymbuddy.auth.models.Profile;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@Table(name = "personal_records_tbl")
public class WorkoutPR extends BaseEntity {
    private String name;
    private Double resistance;
    @ManyToOne
    @JoinColumn(name = "profile_id")
    @JsonBackReference
    private Profile profile;
}
