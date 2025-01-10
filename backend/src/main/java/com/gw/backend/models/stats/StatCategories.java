package com.gw.backend.models.stats;

import com.gw.backend.service.userdetail.stats.*;

import java.util.List;

public enum StatCategories {
	MOVEMENT {
		public ArtMovementStatsService convert(List<StatsService> statsService){
			for (StatsService statService : statsService){
				if (statService instanceof ArtMovementStatsService){
					return (ArtMovementStatsService) statService;
				}
			}
			return null;
		}
	},
	ARTIST{
		public ArtistNameStatsService convert(List<StatsService> statsService) {
			for (StatsService statService : statsService){
				if (statService instanceof ArtistNameStatsService){
					return (ArtistNameStatsService) statService;
				}
			}
			return null;
		}
	},
	TYPE{
		public ArtTypeStatsService convert(List<StatsService>  statsService) {
			for (StatsService statService : statsService){
				if (statService instanceof ArtTypeStatsService){
					return (ArtTypeStatsService) statService;
				}
			}
			return null;
		}

	},
	YEAR{
		public ArtYearFinishedStatsService convert(List<StatsService> statsService) {
			for (StatsService statService : statsService){
				if (statService instanceof ArtYearFinishedStatsService){
					return (ArtYearFinishedStatsService) statService;
				}
			}
			return null;
		}

	}, ;

	public abstract StatsService convert(List<StatsService> statsService);


}
