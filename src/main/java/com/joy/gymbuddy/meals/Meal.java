package com.joy.gymbuddy.meals;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.joy.gymbuddy.BaseEntity;
import com.joy.gymbuddy.auth.models.Profile;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@Table(name = "meals_tbl")
public class Meal extends BaseEntity {
    private String name;
    private String brand;
    @Column(name = "serving")
    private String servingSize;
    private String calories;
    private String fat;
    private String carbs;
    private String protein;
    @CreationTimestamp
    private LocalDate createdAt;
    @UpdateTimestamp
    private LocalDate updatedAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    @JsonBackReference
    private Profile profile;
}
