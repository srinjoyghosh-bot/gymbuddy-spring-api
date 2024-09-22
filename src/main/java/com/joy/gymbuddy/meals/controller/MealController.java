package com.joy.gymbuddy.meals.controller;

import com.joy.gymbuddy.ApiResponse;
import com.joy.gymbuddy.meals.service.MealService;
import com.joy.gymbuddy.meals.models.Meal;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/meal")
@AllArgsConstructor
public class MealController {

    private MealService mealService;

    @GetMapping("/get")
    public ResponseEntity<ApiResponse<List<Meal>>> getMeals(Principal connectedUser, @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Meal> meals= new ArrayList<>();
        if(date == null) {
            meals=mealService.getMealsByProfile(connectedUser);
        } else {
            meals=mealService.getMealsByProfileAndDate(connectedUser, date);
        }

        return new ResponseEntity<>(new ApiResponse<>(true,"Meals fetched",meals), HttpStatus.OK);
    }
}
