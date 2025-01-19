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
public class ArtYearFinishedStatsService extends StatsService {

	private final LikedArtworkRepository likedArtworkRepository;

	private final DislikedArtworkRepository dislikedArtworkRepository;

	@Autowired
	public ArtYearFinishedStatsService(LikedArtworkRepository likedArtworkRepository, DislikedArtworkRepository dislikedArtworkRepository) {
		this.likedArtworkRepository = likedArtworkRepository;
		this.dislikedArtworkRepository = dislikedArtworkRepository;
	}


	@Override
	public List<ArtYearFinished> getStats(SortingCriteria sortBy, Long userId) {
		Set<String> distinctSet = likedArtworkRepository.findDistinctArtYearFinishedByUser(userId);
		distinctSet.addAll(dislikedArtworkRepository.findDistinctArtYearFinishedByUser(userId));
		return distinctSet.stream()
				.map(value -> createStats(value, userId))
				.sorted(sortBy.getComparator())
				.toList();
	}

	@Override
	public ArtYearFinished createStats(String value, Long userId) {
		long likes = likedArtworkRepository.countByArtYearFinishedAndUserId(value, userId);
		long dislikes = dislikedArtworkRepository.countByArtYearFinishedAndUserId(value, userId);
		long total = likes + dislikes;
		Integer percentage = this.findPercentage(likes, dislikes);

		return new ArtYearFinished(value, new Statistics(likes, total, percentage));
	}
}