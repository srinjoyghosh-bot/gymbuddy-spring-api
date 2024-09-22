package com.joy.gymbuddy.workouts.controller;

import com.joy.gymbuddy.ApiResponse;
import com.joy.gymbuddy.workouts.model.entities.WorkoutPR;
import com.joy.gymbuddy.workouts.service.WorkoutService;
import com.joy.gymbuddy.workouts.model.entities.Workout;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/workout")
@AllArgsConstructor
public class WorkoutController {
    private WorkoutService workoutService;
    @GetMapping("/get")
    public ResponseEntity<ApiResponse<List<Workout>>> getWorkouts(Principal connectedUser, @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate  date) {
        List<Workout> workouts=new ArrayList<>();
        if(date == null) {
            workouts=workoutService.getAllWorkouts(connectedUser);
        }else{
            workouts=workoutService.getAllWorkoutsByDate(connectedUser,date);
        }

        return new ResponseEntity<>(new ApiResponse<>(true,"Workouts fetched",workouts), HttpStatus.OK);
    }

    @GetMapping("/pr/get")
    public ResponseEntity<ApiResponse<List<WorkoutPR>>> getWorkoutPrs(Principal connectedUser) {
        var prs=workoutService.getAllWorkoutPRs(connectedUser);
        return new ResponseEntity<>(new ApiResponse<>(true,"Workout PRs fetched",prs), HttpStatus.OK);
    }
}
