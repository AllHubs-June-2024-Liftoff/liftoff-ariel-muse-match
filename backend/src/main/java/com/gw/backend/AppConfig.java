package com.gw.backend;

import com.gw.backend.models.user.UserModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

	@Bean
	public UserModel userModel() {
		return new UserModel();
	}

}
