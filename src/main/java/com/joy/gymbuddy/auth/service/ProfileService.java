package com.joy.gymbuddy.auth.service;

import com.joy.gymbuddy.auth.dto.ProfileDTO;
import com.joy.gymbuddy.auth.models.Profile;
import com.joy.gymbuddy.auth.models.User;
import com.joy.gymbuddy.auth.repo.ProfileRepository;
import com.joy.gymbuddy.meals.Meal;
import com.joy.gymbuddy.meals.MealDTO;
import com.joy.gymbuddy.workouts.*;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Comparator;
import java.util.Objects;
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

    public Profile update(ProfileDTO profileDTO, Principal connectedUser) {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        Optional<Profile> profile = profileRepository.findProfileByUserId(user.getId());
        if (profile.isPresent()) {
            var profileProfile = profile.get();
            profileProfile.setProfilePhoto(profileDTO.profilePhoto());
            profileProfile.setUserName(profileDTO.userName());
            return profileRepository.save(profileProfile);
        }
        return null;
    }

    public Profile get(Principal connectedUser) {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        var profile = profileRepository.findById(user.getId());
        //TODO throw exception for or else
        return profile.orElse(null);
    }

    public Profile addMealToProfile(MealDTO dto, Principal connectedUser) {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        Meal meal = new Meal();
        meal.setBrand(dto.brand());
        meal.setName(dto.name());
        meal.setCalories(dto.calories());
        meal.setFat(dto.fat());
        meal.setCarbs(dto.carbs());
        meal.setProtein(dto.protein());
        meal.setServingSize(dto.servingSize());
        Optional<Profile> profile = profileRepository.findProfileByUserId(user.getId());
        if (profile.isPresent()) {
            var profileProfile = profile.get();
            profileProfile.addMeal(meal);
            return profileRepository.save(profileProfile);
        }
        //TODO throw exception
        return null;
    }

    public void removeMealFromProfile(Integer mealId, Principal connectedUser) {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        Optional<Profile> profile = profileRepository.findProfileByUserId(user.getId());
        if (profile.isPresent()) {
            var profileProfile = profile.get();
            var profileMeals = profileProfile.getMeals();
            var meal = profileMeals.stream().filter(ml -> ml.getId().equals(mealId)).findFirst();
            meal.ifPresent(profileMeals::remove);
            profileRepository.save(profileProfile);
        }
        //TODO throw exception
    }

    public Profile addWorkoutToProfile(WorkoutDTO dto, Principal connectedUser) {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        Workout workout = new Workout();
        workout.setName(dto.name());
        workout.setCategory(dto.category());
        workout.setInstructions(dto.instructions());
        workout.setReps(dto.reps());
        workout.setPrimaryMuscles(dto.primaryMuscles());
        workout.setSecondaryMuscles(dto.secondaryMuscles());
        Optional<Profile> profile = profileRepository.findProfileByUserId(user.getId());
        if (profile.isPresent()) {
            var profileProfile = profile.get();
            profileProfile.addWorkout(workout);
            double maxResistance = workout.getMaxRep();
            // update PR
            var prs = profileProfile.getPrs();
            var pr = prs.stream().filter(record -> record.getName().equals(dto.name())).findFirst();
            var newPr = new WorkoutPR();
            newPr.setName(workout.getName());
            newPr.setResistance(maxResistance);
            if (pr.isEmpty()) {
                profileProfile.addWorkoutPR(newPr);
            } else if (maxResistance > pr.get().getResistance()) {
                pr.get().setResistance(maxResistance);
            }
            return profileRepository.save(profileProfile);
        }
        return null;
    }

    public void removeWorkoutFromProfile(Integer workoutId, Principal connectedUser) {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        Optional<Profile> optionalProfile = profileRepository.findProfileByUserId(user.getId());
        if (optionalProfile.isPresent()) {
            var profile = optionalProfile.get();
            var optionalWorkout = profile.getWorkouts().stream().filter(workout -> workout.getId().equals(workoutId)).findFirst();
            if (optionalWorkout.isPresent()) {
                var workout = optionalWorkout.get();
                var maxRep = workout.getMaxRep();
                profile.removeWorkout(workout);
                var optionalPr = profile.getPrs().stream().filter(pr -> pr.getName().equals(workout.getName())).findFirst();
                if (optionalPr.isPresent()) {
                    var pr = optionalPr.get();
                    if (Objects.equals(maxRep, pr.getResistance())) {
                        var newPr = profile.getWorkouts().stream().filter(workout1 -> workout1.getName().equals(workout.getName())).flatMap(workout1 -> workout1.getReps().stream()).map(Repetition::getResistance).max(Comparator.naturalOrder());
                        if (newPr.isPresent()) {
                            pr.setResistance(newPr.get());
                        } else {
                            profile.removePr(pr);
                        }
                    }
                }
            }
            //TODO else throw exception
            profileRepository.save(profile);
        }
        //TODO else throw exception
    }
}
