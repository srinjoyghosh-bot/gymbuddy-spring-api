package com.joy.gymbuddy.meals;

import com.joy.gymbuddy.auth.models.Profile;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MealService {
    private MealRepository mealRepository;

    public Meal add(MealDTO dto){
        Meal meal = new Meal();
        meal.setBrand(dto.brand());
        meal.setName(dto.name());
        meal.setCalories(dto.calories());
        meal.setFat(dto.fat());
        meal.setCarbs(dto.carbs());
        meal.setProtein(dto.protein());
        meal.setServingSize(dto.servingSize());
        return mealRepository.save(meal);
    }

    public List<Meal> getMealsByProfile(Integer profileId){
        return mealRepository.findAllByProfileId(profileId);
    }
}
