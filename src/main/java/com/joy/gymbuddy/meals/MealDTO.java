package com.joy.gymbuddy.meals;

import jakarta.persistence.Column;

public record MealDTO(String name,
                      String brand,
                      String servingSize,
                      String calories,
                      String fat,
                      String carbs,
                      String protein,
                      Integer profileId) {
}
