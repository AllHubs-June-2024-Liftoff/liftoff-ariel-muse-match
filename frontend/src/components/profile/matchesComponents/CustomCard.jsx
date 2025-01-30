import React from "react";
import ShareMuseButton from "./ShareMuseButton";
import { Card, CardMedia, CardContent, Typography } from "@mui/material";

function CustomCard({
	artistId,
	artistName,
	artworkTypeTitle,
	picture,
	placeOfOrigin,
	styleTitle,
}) {
	const shareUrl = `Check out the artist that I found on MuseMatch!`;

	return (
		<Card sx={{ maxWidth: 300, margin: 2 }}>
			<CardMedia
				component="img"
				height="140"
				image={picture}
				alt={`${artistName}`}
			/>
			<CardContent>
				<Typography gutterBottom variant="h5" component="div">
					{artistName || "Artist Name Not Available"}
				</Typography>
				<Typography variant="body2" color="text.secondary">
					{artworkTypeTitle || "Artwork Type Not Available"}
				</Typography>
				<Typography variant="body2" color="text.secondary">
					{placeOfOrigin || "Place of Origin Not Available"}
				</Typography>
				<Typography variant="body2" color="text.secondary">
					{styleTitle || "Artwork style is not available"}
				</Typography>
				<ShareMuseButton shareUrl={shareUrl} />
			</CardContent>
		</Card>
	);
}

export default CustomCard;
