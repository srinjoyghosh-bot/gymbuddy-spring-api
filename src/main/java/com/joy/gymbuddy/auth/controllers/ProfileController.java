package com.joy.gymbuddy.auth.controllers;

import com.joy.gymbuddy.ApiResponse;
import com.joy.gymbuddy.auth.dto.ProfileDTO;
import com.joy.gymbuddy.auth.service.ProfileService;
import com.joy.gymbuddy.meals.Meal;
import com.joy.gymbuddy.meals.MealDTO;
import com.joy.gymbuddy.workouts.WorkoutDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/profile")
@AllArgsConstructor
public class ProfileController {
    private ProfileService profileService;

    @PostMapping("/meal/add")
    public ResponseEntity<ApiResponse<MealDTO>> addMeal(@RequestBody MealDTO mealDTO, Principal connectedUser) {
        var profile = profileService.addMealToProfile(mealDTO,connectedUser);
        //TODO add meal response dto
        return new ResponseEntity<>(new ApiResponse<>(true, "Meal added", mealDTO), HttpStatus.CREATED);
    }
    @DeleteMapping("/meal/delete/{mealId}")
    public ResponseEntity<ApiResponse<?>> deleteMeal(@PathVariable Integer mealId,Principal connectedUser ) {
        profileService.removeMealFromProfile(mealId, connectedUser);
        return new ResponseEntity<>(new ApiResponse<>(true,"Meal Deleted",null), HttpStatus.OK);
    }

    @PostMapping("/workout/add")
    public ResponseEntity<ApiResponse<WorkoutDTO>> addWorkout(@RequestBody WorkoutDTO workoutDTO,Principal connectedUser) {
        var profile = profileService.addWorkoutToProfile(workoutDTO,connectedUser);
        //TODO add workout response dto
        return new ResponseEntity<>(new ApiResponse<>(true, "Workout added", workoutDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/workout/delete/{workoutId}")
    public ResponseEntity<ApiResponse<?>> deleteWorkout(@PathVariable Integer workoutId,Principal connectedUser ) {
        profileService.removeWorkoutFromProfile(workoutId, connectedUser);
        return new ResponseEntity<>(new ApiResponse<>(true,"Workout Deleted",null), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<ProfileDTO>> updateProfile(@Valid @RequestBody ProfileDTO profileDTO,Principal connectedUser) {
        var profile = profileService.update(profileDTO,connectedUser);
        return new ResponseEntity<>(new ApiResponse<>(true, "Profile updated", profileDTO), HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<ApiResponse<ProfileDTO>> getProfile(Principal connectedUser) {
        var profile = profileService.get(connectedUser);
        if (profile == null) {
            return new ResponseEntity<>(new ApiResponse<>(false, "Profile not found", null), HttpStatus.NOT_FOUND);
        }
        ProfileDTO dto = new ProfileDTO(profile.getUserName(), profile.getProfilePhoto());
        return new ResponseEntity<>(new ApiResponse<>(true, "Profile found", dto), HttpStatus.OK);
    }

}
