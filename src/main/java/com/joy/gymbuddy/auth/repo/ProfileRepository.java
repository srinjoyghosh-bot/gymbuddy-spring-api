package com.joy.gymbuddy.auth.repo;

import com.joy.gymbuddy.auth.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile,Integer> {
    Optional<Profile> findProfileByUserId(Integer user_id);
    Optional<Profile> findProfileByUserEmail(String email);
}
