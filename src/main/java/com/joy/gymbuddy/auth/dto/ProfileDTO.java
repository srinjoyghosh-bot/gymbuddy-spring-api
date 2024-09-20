package com.joy.gymbuddy.auth.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ProfileDTO(@NotNull @NotEmpty String userName, String profilePhoto, @NotNull Integer profileId) {
}
