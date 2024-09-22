package com.joy.gymbuddy.auth.service;

import com.joy.gymbuddy.auth.models.enums.Role;
import com.joy.gymbuddy.auth.models.Profile;
import com.joy.gymbuddy.auth.models.User;
import com.joy.gymbuddy.auth.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository repository;
    public User create(String email,String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        Profile profile = new Profile();
        profile.setUserName("User Name");
        user.setProfile(profile);
        user.setRole(Role.USER);
        profile.setUser(user);
        return repository.save(user);
    }

    public User getUserByEmail(String email) {
        var optionalUser= repository.findByEmail(email);
        //TODO throw exception in or else
        return optionalUser.orElse(null);
    }

}
