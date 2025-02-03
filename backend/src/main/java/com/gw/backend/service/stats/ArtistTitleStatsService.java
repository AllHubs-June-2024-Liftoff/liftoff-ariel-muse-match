package com.gw.backend.service.stats;

import com.gw.backend.models.stats.*;
import com.gw.backend.repository.DislikedArtworkRepository;
import com.gw.backend.repository.LikedArtworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ArtistTitleStatsService extends StatsService{

	private final LikedArtworkRepository likedArtworkRepository;

	private final DislikedArtworkRepository dislikedArtworkRepository;


	@Autowired
	public ArtistTitleStatsService(LikedArtworkRepository likedArtworkRepository, DislikedArtworkRepository dislikedArtworkRepository) {
		this.likedArtworkRepository = likedArtworkRepository;
		this.dislikedArtworkRepository = dislikedArtworkRepository;
	}

	@Override
	public List<ArtistTitle> getStats(SortingCriteria sortBy, Long ownerId) {
		Set<String> distinctSet = likedArtworkRepository.findDistinctArtistTitleByOwner(ownerId);
		distinctSet.addAll(dislikedArtworkRepository.findDistinctArtistTitleByOwner(ownerId));
		return distinctSet.stream()
				.map(value -> createStats(value, ownerId))
				.sorted(sortBy.getComparator())
				.toList();
	}

	@Override
	public ArtistTitle createStats(String value, Long ownerId) {
		long likes = likedArtworkRepository.countByArtistTitleAndOwnerId(value, ownerId);
		long dislikes = dislikedArtworkRepository.countByArtistTitleAndOwnerId(value, ownerId);
		long total = likes + dislikes;
		Integer percentage = this.findPercentage(likes, dislikes);

		return new ArtistTitle(value, new Statistics(likes, total, percentage));
	}
}