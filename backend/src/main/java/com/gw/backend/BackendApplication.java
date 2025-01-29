package com.gw.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {
//	@Autowired
//	private UserRepository userRepository;

//	 Creates a test user with the username: tester and the password: test-password and the role: user

//	@Bean
//	CommandLineRunner runner(){
//		return args -> {
//			User user = new User("tester",
//					"$2a$10$YBoP62YCHKvmCfolnl5VJu8hhNVJFM/XdW2.wGZ6SaF9VAnV7ZvyS",
//					null, "tester@test.com", "user");
//			userRepository.save(user);
//		};
//	}


	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
