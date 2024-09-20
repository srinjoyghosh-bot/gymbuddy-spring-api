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

import java.util.List;

@RestController
@RequestMapping("/profile")
@AllArgsConstructor
public class ProfileController {
    private ProfileService profileService;

    @PostMapping("/meal/add")
    public ResponseEntity<ApiResponse<MealDTO>> addMeal(@RequestBody MealDTO mealDTO) {
        var profile = profileService.addMealToProfile(mealDTO);
        //TODO add meal response dto
        return new ResponseEntity<>(new ApiResponse<>(true, "Meal added", mealDTO), HttpStatus.CREATED);
    }

    @PostMapping("/workout/add")
    public ResponseEntity<ApiResponse<WorkoutDTO>> addWorkout(@RequestBody WorkoutDTO workoutDTO) {
        var profile = profileService.addWorkoutToProfile(workoutDTO);
        //TODO add workout response dto
        return new ResponseEntity<>(new ApiResponse<>(true, "Workout added", workoutDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<ProfileDTO>> updateProfile(@Valid @RequestBody ProfileDTO profileDTO) {
        var profile = profileService.update(profileDTO);
        return new ResponseEntity<>(new ApiResponse<>(true, "Profile updated", profileDTO), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse<ProfileDTO>> getProfile(@PathVariable Integer id) {
        var profile = profileService.get(id);
        if (profile == null) {
            return new ResponseEntity<>(new ApiResponse<>(false, "Profile not found", null), HttpStatus.NOT_FOUND);
        }
        ProfileDTO dto = new ProfileDTO(profile.getUserName(), profile.getProfilePhoto(), profile.getId());
        return new ResponseEntity<>(new ApiResponse<>(true, "Profile found", dto), HttpStatus.OK);
    }

}
