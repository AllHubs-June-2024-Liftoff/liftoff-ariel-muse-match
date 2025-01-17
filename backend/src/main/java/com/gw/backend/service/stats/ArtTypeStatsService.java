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
		Set<String> distinctSet = likedArtworkRepository.findDistinctArtTypeByUser(userId);
		distinctSet.addAll(likedArtworkRepository.findDistinctArtTypeByUser(userId));
		return distinctSet.stream()
				.map(value -> createStats(value, userId))
				.sorted(sortBy.getComparator())
				.collect(Collectors.toList());
	}

	@Override
	public ArtType createStats(String value, Long userId) {
		long likes = likedArtworkRepository.countByArtTypeAndUserId(value, userId);
		long dislikes = dislikedArtworkRepository.countByArtTypeAndUserId(value, userId);
		long total = likes + dislikes;
		Integer percentage = this.findPercentage(likes, dislikes);

		return new ArtType(value, new Statistics(likes, total, percentage));
	}
}