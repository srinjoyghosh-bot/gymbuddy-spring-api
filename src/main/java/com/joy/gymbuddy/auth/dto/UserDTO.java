package com.joy.gymbuddy.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserDTO(
        @Email
        @NotNull(message = "Please provide an email")
        String email,
        @NotNull
        @Size(min=5,max=8,message = "Password must be 5 to 8 characters long")
        String password) {
}
