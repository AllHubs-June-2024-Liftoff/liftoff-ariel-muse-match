package com.gw.backend.service.stats;

import com.gw.backend.models.stats.*;
import com.gw.backend.repository.DislikedArtworkRepository;
import com.gw.backend.repository.LikedArtworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ArtTypeStatsService extends StatsService{

	private final LikedArtworkRepository likedArtworkRepository;

	private final DislikedArtworkRepository dislikedArtworkRepository;

	@Autowired
	public ArtTypeStatsService(LikedArtworkRepository likedArtworkRepository, DislikedArtworkRepository dislikedArtworkRepository) {
		this.likedArtworkRepository = likedArtworkRepository;
		this.dislikedArtworkRepository = dislikedArtworkRepository;
	}


	@Override
	public List<ArtType> getStats(SortingCriteria sortBy, Long userId) {
		Set<String> distinctSet = likedArtworkRepository.findDistinctArtTypeByOwner(userId);
		distinctSet.addAll(dislikedArtworkRepository.findDistinctArtTypeByOwner(userId));
		return distinctSet.stream()
				.map(value -> createStats(value, userId))
				.sorted(sortBy.getComparator())
				.toList();
	}

	@Override
	public ArtType createStats(String value, Long userId) {
		long likes = likedArtworkRepository.countByArtTypeAndOwnerId(value, userId);
		long dislikes = dislikedArtworkRepository.countByArtTypeAndOwnerId(value, userId);
		long total = likes + dislikes;
		Integer percentage = this.findPercentage(likes, dislikes);

		return new ArtType(value, new Statistics(likes, total, percentage));
	}
}