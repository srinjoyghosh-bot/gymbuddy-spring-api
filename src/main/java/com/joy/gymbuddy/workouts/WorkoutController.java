package com.joy.gymbuddy.workouts;

import com.joy.gymbuddy.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/workout")
@AllArgsConstructor
public class WorkoutController {
    private WorkoutService workoutService;
    @GetMapping("/get")
    public ResponseEntity<ApiResponse<List<Workout>>> getWorkouts(@RequestParam Integer profileId, @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Workout> workouts=new ArrayList<>();
        if(date == null) {
            workouts=workoutService.getAllWorkouts(profileId);
        }else{
            workouts=workoutService.getAllWorkoutsByDate(profileId,date);
        }

        return new ResponseEntity<>(new ApiResponse<>(true,"Workouts fetched",workouts), HttpStatus.OK);
    }

    @GetMapping("/pr/get")
    public ResponseEntity<ApiResponse<List<WorkoutPR>>> getWorkoutPrs(@RequestParam Integer profileId) {
        var prs=workoutService.getAllWorkoutPRs(profileId);
        return new ResponseEntity<>(new ApiResponse<>(true,"Workout PRs fetched",prs), HttpStatus.OK);
    }
}
