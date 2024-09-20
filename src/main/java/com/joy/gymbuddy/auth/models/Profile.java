package com.joy.gymbuddy.auth.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.joy.gymbuddy.BaseEntity;
import com.joy.gymbuddy.workouts.Workout;
import com.joy.gymbuddy.workouts.WorkoutPR;
import com.joy.gymbuddy.meals.Meal;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@Table(name = "user_profile")
public class Profile extends BaseEntity {
    private String userName;
    private String profilePhoto;

    @OneToMany(mappedBy = "profile",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonManagedReference
    private List<Workout> workouts;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "profile",orphanRemoval = true)
    @JsonManagedReference
    private List<Meal> meals;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "profile",orphanRemoval = true)
    @JsonManagedReference
    @Column(name = "workout_personal_records")
    private List<WorkoutPR> prs;

    public void addMeal(Meal meal) {
        if(meals == null) {
            meals = new ArrayList<>();
        }
        meals.add(meal);
        meal.setProfile(this);
    }

    public void removeMeal(Meal meal) {
        meals.remove(meal);
        meal.setProfile(null);
    }

    public void addWorkout(Workout workout) {
        if(workouts == null) {
            workouts = new ArrayList<>();
        }
        workouts.add(workout);
        workout.setProfile(this);
    }

    public void removeWorkout(Workout workout) {
        workouts.remove(workout);
        workout.setProfile(null);
    }

    public void addWorkoutPR(WorkoutPR workoutPR) {
        if(prs == null) {
            prs = new ArrayList<>();
        }
        prs.add(workoutPR);
        workoutPR.setProfile(this);
    }
    public void removePr(WorkoutPR workoutPR) {
        prs.remove(workoutPR);
        workoutPR.setProfile(null);
    }
    public void updatePR(WorkoutPR workoutPR,Double resistance) {
        WorkoutPR newPr=new WorkoutPR();
        newPr.setResistance(resistance);
        newPr.setName(workoutPR.getName());
        prs.remove(workoutPR);
        prs.add(newPr);
    }
}
