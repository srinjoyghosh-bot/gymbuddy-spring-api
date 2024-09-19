package com.joy.gymbuddy.auth.service;

import com.joy.gymbuddy.auth.models.Profile;
import com.joy.gymbuddy.auth.repo.ProfileRepository;
import com.joy.gymbuddy.meals.Meal;
import com.joy.gymbuddy.meals.MealDTO;
import com.joy.gymbuddy.meals.MealRepository;
import com.joy.gymbuddy.workouts.Workout;
import com.joy.gymbuddy.workouts.WorkoutDTO;
import com.joy.gymbuddy.workouts.WorkoutPR;
import com.joy.gymbuddy.workouts.WorkoutPRRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProfileService {
    private ProfileRepository profileRepository;
    private WorkoutPRRepository workoutPRRepository;

    public Profile create() {
        Profile profile = new Profile();
        profile.setUserName("User Name");
        return profileRepository.save(profile);
    }

    public Profile addMealToProfile(MealDTO dto) {
        Meal meal = new Meal();
        meal.setBrand(dto.brand());
        meal.setName(dto.name());
        meal.setCalories(dto.calories());
        meal.setFat(dto.fat());
        meal.setCarbs(dto.carbs());
        meal.setProtein(dto.protein());
        meal.setServingSize(dto.servingSize());
        Optional<Profile> profile = profileRepository.findById(dto.profileId());
        if (profile.isPresent()) {
            var profileProfile = profile.get();
            profileProfile.addMeal(meal);
            return profileRepository.save(profileProfile);
        }
        return null;
    }

    public Profile addWorkoutToProfile(WorkoutDTO dto) {
        Workout workout= new Workout();
        workout.setName(dto.name());
        workout.setCategory(dto.category());
        workout.setInstructions(dto.instructions());
        workout.setReps(dto.reps());
        workout.setPrimaryMuscles(dto.primaryMuscles());
        workout.setSecondaryMuscles(dto.secondaryMuscles());
        Optional<Profile> profile = profileRepository.findById(dto.profileId());
        if (profile.isPresent()) {
            var profileProfile = profile.get();
            profileProfile.addWorkout(workout);
            double maxResistance=workout.getMaxRep();
            //TODO update PR
            var pr=workoutPRRepository.findByName(workout.getName());
            var newPr=new WorkoutPR();
            newPr.setName(workout.getName());
            newPr.setResistance(maxResistance);
            if(pr==null){
                profileProfile.addWorkoutPR(newPr);
            }else if(maxResistance>pr.getResistance()){
                workoutPRRepository.delete(pr);
                profileProfile.updatePR(pr,maxResistance);
            }
            return profileRepository.save(profileProfile);
        }
        return null;
    }
}
