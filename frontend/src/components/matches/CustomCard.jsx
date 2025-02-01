import Button from "react-bootstrap/Button";
import { Link } from "react-router-dom";
import Card from "react-bootstrap/Card";
import ShareMuseButton from "./ShareMuseButton.jsx";
import MenuAlignExample from "./Dropdown.jsx";
import getImage from "../image/GetImage.jsx";
import React, { useRef, useEffect, useState } from "react";
import Reflection from "./Reflection.jsx";
import { ButtonGroup } from "react-bootstrap";

export default function CustomCard(match) {
	const [imageUrl, setImageUrl] = useState(null);
	const [loading, setLoading] = useState(true);

	useEffect(() => {
		console.log("This is a matched.picture" + match.imageId);

		const fetchImage = async (picture) => {
			const fetchedImageUrl = await getImage(picture);
			console.log("Fetched image URL:", fetchedImageUrl);
			setImageUrl(fetchedImageUrl);
			setLoading(false);
		};

		fetchImage(match.imageId);
	}, [match.imageId]); // figure this one out
	if (loading) {
		return (
			<Card style={{ width: "18rem" }}>
				<Card.Body>
					<Card.Text>Loading image...</Card.Text>
				</Card.Body>
			</Card>
		);
	}
	return (
		<Card style={{ width: "18rem" }}>
			<Card.Title>
				<Link
					to={`https://api.artic.edu/api/v1/artworks?artist_id=${match.artistId}`}
					style={{ textDecoration: "none" }}
				>
					{match.artistName || "Could not find artist"}
				</Link>
			</Card.Title>
			<Card.Img
				style={{
					objectFit: "contain",
					height: "200px",
					width: "100%",
				}}
				variant="top"
				src={imageUrl}
			/>
			<Card.Body>
				<Card.Title>{match.styleTitle || "Could not find style"}</Card.Title>
				<Card.Title>{match.artType || "Could not find art type"}</Card.Title>
				<Card.Title>
					{match.placeOfOrigin || "Could not find place of origin"}
				</Card.Title>
				<Card.Text>Description</Card.Text>
				<ButtonGroup>
					<Button variant="primary">
						<MenuAlignExample />
					</Button>
					<Reflection matchId={match.matchId} reflection={match.reflection} />
				</ButtonGroup>
			</Card.Body>
		</Card>
	);
}
