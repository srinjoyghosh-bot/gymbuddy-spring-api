package com.joy.gymbuddy.meals.service;

import com.joy.gymbuddy.auth.models.User;
import com.joy.gymbuddy.meals.models.Meal;
import com.joy.gymbuddy.meals.repo.MealRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class MealService {
    private MealRepository mealRepository;

    public List<Meal> getMealsByProfile(Principal connectedUser){
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        var profile = user.getProfile();
        return mealRepository.findAllByProfileId(profile.getId());
    }

    public List<Meal> getMealsByProfileAndDate(Principal connectedUser, LocalDate date){
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        var profile = user.getProfile();
        return mealRepository.findAllByCreatedAtAndProfileId(date, profile.getId());
    }
}
