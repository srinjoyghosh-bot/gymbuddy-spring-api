package com.joy.gymbuddy.auth.controllers;

import com.joy.gymbuddy.ApiResponse;
import com.joy.gymbuddy.auth.dto.AuthResponseDTO;
import com.joy.gymbuddy.auth.dto.UserDTO;
import com.joy.gymbuddy.auth.models.Profile;
import com.joy.gymbuddy.auth.models.User;
import com.joy.gymbuddy.auth.service.JwtService;
import com.joy.gymbuddy.auth.service.ProfileService;
import com.joy.gymbuddy.auth.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private UserService userService;
    private ProfileService profileService;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;
    private AuthenticationManager authenticationManager;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<AuthResponseDTO>> createUser(@Valid @RequestBody UserDTO dto) {
        var user = userService.getUserByEmail(dto.email());
        if (user != null) {
            return new ResponseEntity<>(new ApiResponse<>(false, "Email already exists", null), HttpStatus.CONFLICT);
        }
        var savedUser = userService.create(dto.email(), passwordEncoder.encode(dto.password()));
        var token = jwtService.generateToken(savedUser);
        return new ResponseEntity<>(new ApiResponse<>(true, "User created", new AuthResponseDTO(token)), HttpStatus.CREATED);
    }

    @GetMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponseDTO>> login(@Valid @RequestBody UserDTO dto) {
        var user = userService.getUserByEmail(dto.email());
        if (user == null) {
            return new ResponseEntity<>(new ApiResponse<>(false, "User not found", null), HttpStatus.NOT_FOUND);
        }
        if (!passwordEncoder.matches(dto.password(), user.getPassword())) {
            return new ResponseEntity<>(new ApiResponse<>(false, "Wrong password", null), HttpStatus.UNAUTHORIZED);
        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.email(), dto.password()));

        var token = jwtService.generateToken(user);
        AuthResponseDTO loginResponseDTO = new AuthResponseDTO(token);

        return new ResponseEntity<>(new ApiResponse<>(true, "User logged", loginResponseDTO), HttpStatus.OK);
    }
}
