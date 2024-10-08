package com.joy.gymbuddy.workouts.service;

import com.joy.gymbuddy.auth.models.User;
import com.joy.gymbuddy.workouts.model.entities.Workout;
import com.joy.gymbuddy.workouts.model.entities.WorkoutPR;
import com.joy.gymbuddy.workouts.repo.WorkoutPRRepository;
import com.joy.gymbuddy.workouts.repo.WorkoutRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class WorkoutService {
    private WorkoutRepository workoutRepository;
    private WorkoutPRRepository workoutPRRepository;

    public List<Workout> getAllWorkouts(Principal connectedUser) {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        return workoutRepository.findAllByProfileId(user.getProfile().getId());
    }

    public List<Workout> getAllWorkoutsByDate(Principal connectedUser, LocalDate date) {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        return workoutRepository.findAllByUpdatedAtAndProfileId(date, user.getProfile().getId());
    }

    public List<WorkoutPR> getAllWorkoutPRs(Principal connectedUser) {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        return workoutPRRepository.findAllByProfileId(user.getProfile().getId());
    }
}
