package com.joy.gymbuddy;

public record ApiResponse<T>(boolean success, String message, T data) {
}
