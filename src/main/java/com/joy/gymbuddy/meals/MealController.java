package com.joy.gymbuddy.meals;

import com.joy.gymbuddy.ApiResponse;
import com.joy.gymbuddy.auth.service.ProfileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meal")
@AllArgsConstructor
public class MealController {

    private MealService mealService;

    @GetMapping("/get")
    public ResponseEntity<ApiResponse<List<Meal>>> getMeals(@RequestParam Integer profileId) {
        var meals=mealService.getMealsByProfile(profileId);
        return new ResponseEntity<>(new ApiResponse<>(true,"Meals fetched",meals), HttpStatus.OK);
    }
}
