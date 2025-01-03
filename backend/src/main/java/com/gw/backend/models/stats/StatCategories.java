package com.gw.backend.models.stats;

import com.gw.backend.service.userdetail.stats.*;

public enum StatCategories {
	MOVEMENT {
		public ArtMovementStatsService convert(StatsService statsService){
			return (ArtMovementStatsService) statsService;
		}
	},
	ARTIST{
		public ArtistNameStatsService convert(StatsService statsService) {
			return (ArtistNameStatsService) statsService;
		}
	},
	TYPE{
		public ArtTypeStatsService convert(StatsService statsService) {
			return (ArtTypeStatsService) statsService;
		}
	},
	YEAR{
		public ArtYearFinishedStatsService convert(StatsService statsService) {
			return (ArtYearFinishedStatsService) statsService;
		}
	};

	public abstract StatsService convert(StatsService statsService);


}
