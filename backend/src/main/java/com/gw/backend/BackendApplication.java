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

//	@Bean
//	CommandLineRunner runner(){
//		return args -> {
//			userRepository.save(new User("midas",
//					"$2a$10$QWSzCJbF0Za/2v.aA2wcGuRMCZMyqC226Db5A89UeELwjryrCuA3a",
//					null, "midas@touch.com", "user"));
//		};
//	}

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
