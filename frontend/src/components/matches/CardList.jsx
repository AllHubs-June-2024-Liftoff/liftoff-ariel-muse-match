import React, { useEffect, useState } from "react";
import { useAuth } from "../auth/AuthContext";
import Card from "react-bootstrap/Card";
import Col from "react-bootstrap/Col";
import Row from "react-bootstrap/Row";
import CustomCard from "./CustomCard.jsx";

export default function CardList() {
	const [matches, setMatches] = useState([]);
	const [loading, setLoading] = useState(true);
	useEffect(() => {
		const fetchMatches = async () => {
			try {
				const response = await fetch(`http://localhost:8080/matches`, {
					method: "GET",
					headers: {
						"Content-Type": "application/json",
					},
					credentials: "include",
				});
				const data = await response.json();
				setMatches(data);
				setLoading(false);
			} catch (error) {
				setLoading(false);
			}
		};

		fetchMatches();
	}, []);

	if (loading) {
		return <div>Loading...</div>;
	}

	return (
		<>
			<h1 style={{ textAlign: "center" }}>Matches</h1>
			<Row xs={1} md={2} lg={3} className="g-4">
				{matches.map((match, idx, matches) => (
					<Col key={idx}>
						<CustomCard
							matchId={match.id}
							reflection={match.reflection}
							artistName={match.artistTitle}
							styleTitle={match.artMovement}
							artType={match.artType}
							placeOfOrigin={match.placeOfOrigin}
							imageId={match.imageId}
							artistId={match.artistId}
						></CustomCard>
					</Col>
				))}
			</Row>
		</>
	);
}
