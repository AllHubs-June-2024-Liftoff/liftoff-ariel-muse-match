import React, { useEffect, useState } from "react";
import CustomCard from "./CustomCard";

function CardList() {
	const [matches, setMatches] = useState([]);
	const [loading, setLoading] = useState(true);
	useEffect(() => {
		const fetchMatches = async () => {
			try {
				const response = await fetch(`http://localhost:8080/api/matches`, {
					method: "GET",
					headers: {
						"Content-Type": "application/json",
					},
					credentials: "include",
				});

				if (!response.ok) {
					throw new Error("Failed to fetch matches");
				}
				const data = await response.json();
				console.log("Fetched matches data:", data);
				setMatches(data);
				setLoading(false);
				console.log(data);
			} catch (error) {
				console.error("Error fetching matches:", error);
				setLoading(false);
			}
		};

		fetchMatches();
	}, []);

	if (loading) {
		return <div>Loading...</div>;
	}

	return (
		<div style={{ display: "flex", flexWrap: "wrap", gap: "16px" }}>
			{matches.map((match) => (
				<CustomCard
					key={match.id}
					artistId={match.artistId}
					artistName={match.artistName}
					artworkTypeTitle={match.artworkTypeTitle}
					picture={match.picture}
					placeOfOrigin={match.placeOfOrigin}
					styleTitle={match.styleTitle}
				/>
			))}
		</div>
	);
}

export default CardList;
