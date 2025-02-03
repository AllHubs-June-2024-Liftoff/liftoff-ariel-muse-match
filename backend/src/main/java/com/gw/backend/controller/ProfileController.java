package com.gw.backend.controller;

import com.gw.backend.dto.MilestoneDto;
import com.gw.backend.dto.PublicProfileDto;
import com.gw.backend.models.achievements.Milestone;
import com.gw.backend.models.stats.SortingCriteria;
import com.gw.backend.models.stats.StatCategories;
import com.gw.backend.models.stats.StatsCategory;
import com.gw.backend.models.user.User;
import com.gw.backend.repository.achievements.MilestoneRepository;
import com.gw.backend.repository.user.UserRepository;
import com.gw.backend.service.UserService;
import com.gw.backend.service.achievements.MilestoneService;
import com.gw.backend.service.achievements.StreakService;
import com.gw.backend.service.stats.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("api/profile")
public class ProfileController {

	private final List<StatsService> statsService;
	private final UserRepository userRepository;

	private final StreakService streakService;

	private final MilestoneService milestoneService;

	private  final UserService userService;


	private final MilestoneRepository milestoneRepository;

	@Autowired
	public ProfileController(List<StatsService> statsService, UserRepository userRepository, StreakService streakService,
							 MilestoneRepository milestoneRepository, MilestoneService milestoneService, UserService userService) {
		this.statsService = statsService;
		this.userRepository = userRepository;
		this.streakService = streakService;
		this.milestoneRepository = milestoneRepository;
		this.milestoneService = milestoneService;
		this.userService = userService;
	}

	@GetMapping("/stats/{category}/{sortBy}")
	public ResponseEntity<List<? extends StatsCategory>> deliverSortedStats(@PathVariable String category, @PathVariable String sortBy, Authentication authentication) {
		String username = authentication.getName();
		User owner = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found: " + username));
		List<? extends StatsCategory> stats = StatCategories.valueOf(category.toUpperCase())
				.convert(statsService)
				.getStats(SortingCriteria
						.valueOf(sortBy.toUpperCase()), owner.getId());
		return new ResponseEntity<>(stats, HttpStatus.OK);
	}

//	@GetMapping("/streaks/update")
//	public ResponseEntity<String> updateStreak(Authentication authentication) {
//		String username = authentication.getName();
//		User owner = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found: " + username));
//		streakService.updateStreak(owner);
//		return ResponseEntity.ok("LikeStreak updated");
//	}
//
//
	@GetMapping("/milestone/all")
	public ResponseEntity<?> getAllMilestones(Authentication authentication) {
		String owner = authentication.getName();
		try {
			List<Milestone> milestones = milestoneService.getUserMilestones(owner);
			List<MilestoneDto> milestoneDtos = milestones.stream().map(MilestoneDto::new).collect(Collectors.toList());
			return ResponseEntity.ok(milestoneDtos);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}



	@GetMapping("/milestone/type/{type}")
	public ResponseEntity<?> getAllMilestonesByType(@PathVariable String type, Authentication authentication) {
		String owner = authentication.getName();
		try {
			List<Milestone> milestones = milestoneService.getMilestonesByType(owner, type);
			List<MilestoneDto> milestoneDtos = milestones.stream().map(MilestoneDto::new).collect(Collectors.toList());

			return ResponseEntity.ok(milestoneDtos);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/{username}")
	public ResponseEntity<?> getPublicProfile(@PathVariable String username) {
		try {
			PublicProfileDto publicProfileDto = userService.getPublicProfile(username);
			return ResponseEntity.ok(publicProfileDto);
		}
		catch (RuntimeException ex){
			return ResponseEntity.notFound().build();
		}
		catch (AccessDeniedException ex) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
	}


	@PutMapping("/update/bio")
	public ResponseEntity<?>  updateBio(@RequestBody String bio, Authentication authentication) {
		String owner = authentication.getName();
		try {
			if (owner == null){
				throw new AccessDeniedException("Access denied.");
			}
			userService.updateBio(owner, bio);
			return ResponseEntity.ok("Updated bio.");
		}
		catch (RuntimeException ex){
			return ResponseEntity.notFound().build();
		}
		catch (AccessDeniedException ex) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
	}

	@PutMapping("/update/visibility")
	public ResponseEntity<?>  updateProfileVisibility(@RequestBody Boolean isPublic, Authentication authentication) {
		String owner = authentication.getName();
		try {
			if (owner == null){
				throw new AccessDeniedException("Access denied.");
			}
			System.out.println("Set to: " + isPublic);
			userService.updateProfileVisibility(owner, isPublic);
			return ResponseEntity.ok("Updated profile visibility.");
		}
		catch (RuntimeException ex){
			return ResponseEntity.notFound().build();
		}
		catch (AccessDeniedException ex) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
	}


}
