package com.gw.backend;

import com.gw.backend.models.user.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

	@Bean
	public User userModel() {
		return new User();
	}

}
