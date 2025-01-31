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
	public List<ArtType> getStats(SortingCriteria sortBy, Long ownerId) {
		Set<String> distinctSet = likedArtworkRepository.findDistinctArtTypeByOwner(ownerId);
		distinctSet.addAll(dislikedArtworkRepository.findDistinctArtTypeByOwner(ownerId));
		return distinctSet.stream()
				.map(value -> createStats(value, ownerId))
				.sorted(sortBy.getComparator())
				.toList();
	}

	@Override
	public ArtType createStats(String value, Long ownerId) {
		long likes = likedArtworkRepository.countByArtTypeAndOwnerId(value, ownerId);
		long dislikes = dislikedArtworkRepository.countByArtTypeAndOwnerId(value, ownerId);
		long total = likes + dislikes;
		Integer percentage = this.findPercentage(likes, dislikes);

		return new ArtType(value, new Statistics(likes, total, percentage));
	}
}