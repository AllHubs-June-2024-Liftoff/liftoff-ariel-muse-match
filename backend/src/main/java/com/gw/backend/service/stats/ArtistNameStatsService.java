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
public class ArtistNameStatsService extends StatsService{

	private final LikedArtworkRepository likedArtworkRepository;

	private final DislikedArtworkRepository dislikedArtworkRepository;


	@Autowired
	public ArtistNameStatsService(LikedArtworkRepository likedArtworkRepository, DislikedArtworkRepository dislikedArtworkRepository) {
		this.likedArtworkRepository = likedArtworkRepository;
		this.dislikedArtworkRepository = dislikedArtworkRepository;
	}

	@Override
	public List<ArtistName> getStats(SortingCriteria sortBy, Long userId) {
		Set<String> distinctSet = likedArtworkRepository.findDistinctArtistNameByUser(userId);
		distinctSet.addAll(likedArtworkRepository.findDistinctArtistNameByUser(userId));
		return distinctSet.stream()
				.map(value -> createStats(value, userId))
				.sorted(sortBy.getComparator())
				.collect(Collectors.toList());
	}

	@Override
	public ArtistName createStats(String value, Long userId) {
		long likes = likedArtworkRepository.countByArtistNameAndUserId(value, userId);
		long dislikes = dislikedArtworkRepository.countByArtistNameAndUserId(value, userId);
		long total = likes + dislikes;
		Integer percentage = this.findPercentage(likes, dislikes);

		return new ArtistName(value, new Statistics(likes, total, percentage));
	}
}