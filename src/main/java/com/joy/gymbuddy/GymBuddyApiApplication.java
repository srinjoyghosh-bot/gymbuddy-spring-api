package com.joy.gymbuddy;

import com.joy.gymbuddy.auth.models.User;
import com.joy.gymbuddy.auth.repo.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.joy.gymbuddy")
public class GymBuddyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GymBuddyApiApplication.class, args);
	}

//	@Bean
	public CommandLineRunner commandLineRunner( UserRepository repository ){
		return args -> {
			var user=User.builder()
					.email("abc@gmail.com")
					.password("123456")
					.build();
			repository.save(user);
		};
	}



}
