package com.gw.backend.service.stats;

import com.gw.backend.models.stats.*;
import com.gw.backend.repository.DislikedArtworkRepository;
import com.gw.backend.repository.LikedArtworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ArtMovementStatsService extends StatsService{

	private final LikedArtworkRepository likedArtworkRepository;

	private final DislikedArtworkRepository dislikedArtworkRepository;

	@Autowired
	public ArtMovementStatsService(LikedArtworkRepository likedArtworkRepository, DislikedArtworkRepository dislikedArtworkRepository) {
		this.likedArtworkRepository = likedArtworkRepository;
		this.dislikedArtworkRepository = dislikedArtworkRepository;
	}

	@Override
	public List<ArtMovement> getStats(SortingCriteria sortBy, Long userId) {
		Set<String> distinctSet = likedArtworkRepository.findDistinctArtMovementByOwner(userId);
		distinctSet.addAll(dislikedArtworkRepository.findDistinctArtMovementByOwner(userId));
		return distinctSet.stream()
				.map(value -> createStats(value, userId))
				.sorted(sortBy.getComparator())
				.toList();
	}

	@Override
	public ArtMovement createStats(String value, Long userId) {
		long likes = likedArtworkRepository.countByArtMovementAndOwnerId(value, userId);
		long dislikes = dislikedArtworkRepository.countByArtMovementAndOwnerId(value, userId);
		long total = likes + dislikes;
		Integer percentage = this.findPercentage(likes, dislikes);

		return new ArtMovement(value, new Statistics(likes, total, percentage));
	}
}