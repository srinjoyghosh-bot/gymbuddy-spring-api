package com.joy.gymbuddy.meals;

import com.joy.gymbuddy.ApiResponse;
import com.joy.gymbuddy.auth.service.ProfileService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/meal")
@AllArgsConstructor
public class MealController {

    private MealService mealService;

    @GetMapping("/get")
    public ResponseEntity<ApiResponse<List<Meal>>> getMeals(@RequestParam Integer profileId,@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Meal> meals= new ArrayList<>();
        if(date == null) {
            meals=mealService.getMealsByProfile(profileId);
        } else {
            meals=mealService.getMealsByProfileAndDate(profileId, date);
        }

        return new ResponseEntity<>(new ApiResponse<>(true,"Meals fetched",meals), HttpStatus.OK);
    }
}
