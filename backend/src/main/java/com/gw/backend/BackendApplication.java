package com.gw.backend;

import com.gw.backend.models.user.User;
import com.gw.backend.models.user.image.ProfilePicture;
import com.gw.backend.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class BackendApplication {
	@Autowired
	private UserRepository userRepository;

	// Creates a test user with the username: tester and the password: test-password and the role: user

//	@Bean
//	CommandLineRunner runner(){
//		return args -> {
//			userRepository.save(new User("tester",
//					"$2a$10$YBoP62YCHKvmCfolnl5VJu8hhNVJFM/XdW2.wGZ6SaF9VAnV7ZvyS",
//					null, "tester@test.com", "user"));
//		};
//	}

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
