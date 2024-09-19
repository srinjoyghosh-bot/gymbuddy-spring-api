package com.joy.gymbuddy.workouts;

import com.joy.gymbuddy.BaseEntity;
import jakarta.persistence.Entity;
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
}
