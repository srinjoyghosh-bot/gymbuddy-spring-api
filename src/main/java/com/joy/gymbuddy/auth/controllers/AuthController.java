package com.joy.gymbuddy.auth.controllers;

import com.joy.gymbuddy.ApiResponse;
import com.joy.gymbuddy.auth.dto.LoginResponseDTO;
import com.joy.gymbuddy.auth.dto.UserDTO;
import com.joy.gymbuddy.auth.models.Profile;
import com.joy.gymbuddy.auth.models.User;
import com.joy.gymbuddy.auth.service.ProfileService;
import com.joy.gymbuddy.auth.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private UserService userService;
    private ProfileService profileService;
    private PasswordEncoder passwordEncoder;
    @PostMapping("/add")
    public ResponseEntity<ApiResponse<UserDTO>> createUser(@Valid @RequestBody UserDTO dto) {
        var user=userService.getUserByEmail(dto.email());
        if(user!=null){
            return new ResponseEntity<>(new ApiResponse<>(false,"Email already exists",null),HttpStatus.CONFLICT);
        }
        Profile profile = profileService.create();
        User savedUser = userService.create(dto.email(),passwordEncoder.encode(dto.password()),profile);
        return new ResponseEntity<>(new ApiResponse<>(true,"User created",dto), HttpStatus.CREATED);
    }

    @GetMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponseDTO>> login(@Valid @RequestBody UserDTO dto){
        var user=userService.getUserByEmail(dto.email());
        if(user==null){
            return new ResponseEntity<>(new ApiResponse<>(false,"User not found",null),HttpStatus.NOT_FOUND);
        }
        if(!passwordEncoder.matches(dto.password(),user.getPassword())){
            return new ResponseEntity<>(new ApiResponse<>(false,"Wrong password",null),HttpStatus.UNAUTHORIZED);
        }
        LoginResponseDTO loginResponseDTO=new LoginResponseDTO(user.getProfile().getId());

        return new ResponseEntity<>(new ApiResponse<>(true,"User logged",loginResponseDTO),HttpStatus.OK);
    }
}
