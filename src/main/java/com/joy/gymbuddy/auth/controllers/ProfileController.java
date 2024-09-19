package com.joy.gymbuddy.auth.controllers;

import com.joy.gymbuddy.ApiResponse;
import com.joy.gymbuddy.auth.service.ProfileService;
import com.joy.gymbuddy.meals.MealDTO;
import com.joy.gymbuddy.workouts.WorkoutDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
@AllArgsConstructor
public class ProfileController {
    private ProfileService profileService;

    @PostMapping("/meal/add")
    public ResponseEntity<ApiResponse<MealDTO>> addMeal(@RequestBody MealDTO mealDTO) {
        var profile=profileService.addMealToProfile(mealDTO);
        //TODO add meal response dto
        return new ResponseEntity<>(new ApiResponse<>(true,"Meal added",mealDTO), HttpStatus.CREATED);
    }

    @PostMapping("/workout/add")
    public ResponseEntity<ApiResponse<WorkoutDTO>> addWorkout(@RequestBody WorkoutDTO workoutDTO) {
        var profile=profileService.addWorkoutToProfile(workoutDTO);
        //TODO add workout response dto
        return new ResponseEntity<>(new ApiResponse<>(true,"Workout added",workoutDTO), HttpStatus.CREATED);
    }

}
