package com.joy.gymbuddy.auth.service;

import com.joy.gymbuddy.auth.dto.UserDTO;
import com.joy.gymbuddy.auth.models.Profile;
import com.joy.gymbuddy.auth.models.User;
import com.joy.gymbuddy.auth.repo.UserRepository;
import com.joy.gymbuddy.meals.Meal;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository repository;
    public User create(String email,String password ,Profile profile) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setProfile(profile);
        return repository.save(user);
    }

    public User getUserByEmail(String email) {
        return repository.findByEmail(email);
    }

}
