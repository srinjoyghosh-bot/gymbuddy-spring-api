package com.joy.gymbuddy.meals;

import com.joy.gymbuddy.auth.models.Profile;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class MealService {
    private MealRepository mealRepository;

    public List<Meal> getMealsByProfile(Integer profileId){
        return mealRepository.findAllByProfileId(profileId);
    }

    public List<Meal> getMealsByProfileAndDate(Integer profileId, LocalDate date){
        return mealRepository.findAllByCreatedAtAndProfileId(date,profileId);
    }
}
