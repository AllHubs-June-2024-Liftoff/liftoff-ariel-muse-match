package com.gw.backend.controller;

import com.gw.backend.models.stats.SortingCriteria;
import com.gw.backend.models.stats.StatCategories;
import com.gw.backend.models.stats.StatsCategory;
import com.gw.backend.models.user.User;
import com.gw.backend.repository.user.UserRepository;
import com.gw.backend.service.stats.StatsService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("profile")
public class ProfileController {


	private final List<StatsService> statsService;
	private final UserRepository userRepository;

	@Autowired
	public ProfileController(List<StatsService> statsService, UserRepository userRepository) {
		this.statsService = statsService;
		this.userRepository = userRepository;
	}



	@GetMapping("/stats/{category}/{sortBy}")
	public List<? extends StatsCategory> deliverSortedStats(@PathVariable String category, @PathVariable String sortBy, Authentication authentication) {

		String username = authentication.getName();
		User owner = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found: " + username));

		return (
				StatCategories.valueOf(category.toUpperCase())
						.convert(statsService)
						.getStats(SortingCriteria
								.valueOf(sortBy.toUpperCase()), owner.getId())
		);
	}

}
