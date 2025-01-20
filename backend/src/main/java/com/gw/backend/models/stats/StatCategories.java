package com.gw.backend.models.stats;

import com.gw.backend.service.stats.*;

import java.util.List;

public enum StatCategories {
	MOVEMENT {
		public ArtMovementStatsService convert(List<StatsService> statsService) {
			for (StatsService statService : statsService) {
				if (statService.getClass() == ArtMovementStatsService.class) {
					return (ArtMovementStatsService) statService;
				}
			}
			return null;
		}
	},
	ARTIST {
		public ArtistTitleStatsService convert(List<StatsService> statsService) {
			for (StatsService statService : statsService) {
				if (statService.getClass() == ArtistTitleStatsService.class) {
					return (ArtistTitleStatsService) statService;
				}
			}
			return null;
		}
	},
	TYPE {
		public ArtTypeStatsService convert(List<StatsService> statsService) {
			for (StatsService statService : statsService) {
				if (statService.getClass() == ArtTypeStatsService.class) {
					return (ArtTypeStatsService) statService;
				}
			}
			return null;
		}

	},
	YEAR {
		public ArtYearFinishedStatsService convert(List<StatsService> statsService) {
			for (StatsService statService : statsService) {
				if (statService.getClass() == ArtYearFinishedStatsService.class) {
					return (ArtYearFinishedStatsService) statService;
				}
			}
			return null;
		}

	},
	;

	public abstract StatsService convert(List<StatsService> statsService);


}
