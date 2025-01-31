package com.gw.backend.service.stats;

import com.gw.backend.models.stats.*;
import com.gw.backend.repository.DislikedArtworkRepository;
import com.gw.backend.repository.LikedArtworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

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
	public List<ArtYearFinished> getStats(SortingCriteria sortBy, Long ownerId) {
		Set<Integer> distinctSet = likedArtworkRepository.findDistinctArtYearFinishedByOwner(ownerId);
		distinctSet.addAll(dislikedArtworkRepository.findDistinctArtYearFinishedByOwner(ownerId));
		return distinctSet.stream()
				.map(value -> createStats(String.valueOf(value), ownerId))
				.sorted(sortBy.getComparator())
				.toList();
	}

	@Override
	public ArtYearFinished createStats(String value, Long ownerId) {
		long likes = likedArtworkRepository.countByArtYearFinishedAndOwnerId(value, ownerId);
		long dislikes = dislikedArtworkRepository.countByArtYearFinishedAndOwnerId(value, ownerId);
		long total = likes + dislikes;
		Integer percentage = this.findPercentage(likes, dislikes);

		return new ArtYearFinished(value, new Statistics(likes, total, percentage));
	}
}