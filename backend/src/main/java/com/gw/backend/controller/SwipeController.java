package com.gw.backend.controller;

import com.gw.backend.models.user.UserPreferences;
import com.gw.backend.repository.user.UserPreferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("swipe")
public class SwipeController {

	private final UserPreferencesRepository repository;

	@Autowired
	public SwipeController(UserPreferencesRepository repository) {
		this.repository = repository;
	}

	@PostMapping()
	public void acceptLike(@RequestBody UserPreferences userPreferencesModel) {
		repository.save(userPreferencesModel);
	}
}
