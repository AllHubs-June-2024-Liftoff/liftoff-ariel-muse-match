package com.gw.backend.service.stats;

import com.gw.backend.models.stats.*;
import com.gw.backend.repository.DislikedArtworkRepository;
import com.gw.backend.repository.LikedArtworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
	public List<ArtMovement> getStats(SortingCriteria sortBy, Long ownerId) {
		Set<String> distinctSet = likedArtworkRepository.findDistinctArtMovementByOwner(ownerId);
		distinctSet.addAll(dislikedArtworkRepository.findDistinctArtMovementByOwner(ownerId));
		return distinctSet.stream()
				.map(value -> createStats(value, ownerId))
				.sorted(sortBy.getComparator())
				.toList();
	}

	@Override
	public ArtMovement createStats(String value, Long ownerId) {
		long likes = likedArtworkRepository.countByArtMovementAndOwnerId(value, ownerId);
		long dislikes = dislikedArtworkRepository.countByArtMovementAndOwnerId(value, ownerId);
		long total = likes + dislikes;
		Integer percentage = this.findPercentage(likes, dislikes);

		return new ArtMovement(value, new Statistics(likes, total, percentage));
	}
}