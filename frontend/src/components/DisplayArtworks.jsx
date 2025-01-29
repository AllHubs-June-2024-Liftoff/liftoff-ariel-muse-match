import React, { useState, useEffect } from "react";
import getImage from "./image/GetImage";
import fetchArtworks from "./match/FetchArtworks";
import "../styles/App.css";
import TinderCard from "react-tinder-card";
import { useAuth } from "../components/auth/AuthContext";

function DisplayArtworks() {
	const [artworks, setArtworks] = useState([]);
	const [loading, setLoading] = useState(true);
	const [error, setError] = useState(null);
	const [imageSources, setImageSources] = useState({});
	const [currentIndex, setCurrentIndex] = useState(0);
	const { getCsrfToken } = useAuth();

	useEffect(() => {
		const loadArtwork = async () => {
			try {
				const data = await fetchArtworks();
				console.log(data);
				if (Array.isArray(data) && data.length > 0) {
					//makes sure the data is an array before being able to map it and set it to the state
					setArtworks(data); //This sets the artworks to the data from the fetchArtworks function
					const sources = {}; //Key: artwork ID, Value: the returned URL from the getImage() function (the image source)
					await Promise.all(
						data.map(async (artwork) => {
							if (artwork.image_id) {
								try {
									sources[artwork.id] = await getImage(artwork.image_id); //This is creating an object where every artwork Id is the key and the value is the image source (Link)
									//{
									//  123: "https://www.artic.edu/iiif/2/123/full/843,/0/default.jpg",} <---Creates this with each artwork ID
								} catch (e) {
									console.error(`Failed to fetch image for ${artwork.id}`, e); //If there is an error, the image source will be null
									sources[artwork.id] = null;
									delete sources[artwork.id]; //If there is an error, the key will be deleted from the object
									console.log(`Deleted artwork ID: ${artwork.id}`); //This is just a console log to show that the key was deleted
								}
							}
						})
					);

					setImageSources(sources);
				} else {
					setError("No data available");
				}
				setLoading(false);
			} catch (err) {
				setError(err.message);
				setLoading(false);
			}
		};
		loadArtwork();
	}, []);

	if (loading) {
		return <div>Loading...</div>;
	}

	if (error) {
		return <div>Error: {error}</div>;
	}

	const swiped = (direction, swipedArtwork) => {
		console.log("You swiped: " + direction + " on " + swipedArtwork.id);

		if (direction === "right" && swipedArtwork) {
			console.log(`You liked ${swipedArtwork.title}`);
			sendLike(swipedArtwork);
		} else if (direction === "left" && swipedArtwork) {
			console.log(`You disliked ${swipedArtwork.title}`);
			sendDislike(swipedArtwork);
		}
	};

	//Logic for sending a like to the backend
	const sendLike = async (artwork) => {
		if (!artwork) return;

		const likedArtwork = {
			artworkId: artwork.id,
			title: artwork.title,
			altText: artwork.thumbnail?.alt_text,
			placeOfOrigin: artwork.place_of_origin,
			description: artwork.description,
			artType: artwork.artwork_type_title,
			artistId: artwork.artist_id,
			artistTitle: artwork.artist_title,
			artMovement: artwork.style_title,
			artMovement: artwork.style_title,
			imageId: artwork.image_id,
			artYearFinished: artwork.date_end,
		};
		console.log(JSON.stringify(likedArtwork));
		const token = await getCsrfToken();
		console.log(token);
		fetch("http://localhost:8080/api/like/save", {
			method: "PUT",
			headers: {
				"Content-Type": "application/json",
				"X-XSRF-TOKEN": token,
			},
			credentials: "include",
			body: JSON.stringify(likedArtwork), //better to deserialize on the front end rather than the backend (more efficient)
		})
			.then((response) => {
				if (!response.ok) {
					throw new Error("Failed to save like");
				}
				return response.json();
			})
			.then((data) => {
				console.log("Like saved successfully:", data);
			})
			.catch((error) => {
				console.error("Error saving like:", error);
			});
	};

	//Logic for sending a dislike to the backend
	const sendDislike = async (artwork) => {
		if (!artwork) return;

        const dislikedArtwork = {
          artworkId: artwork.id,
          title: artwork.title,
          altText: artwork.thumbnail?.alt_text,
          placeOfOrigin: artwork.place_of_origin,
          description: artwork.description,
          artType: artwork.artwork_type_title,
          artistId: artwork.artist_id,
          artistTitle: artwork.artist_title,
          artMovement: artwork.style_title,
          imageId: artwork.image_id,
          artYearFinished: artwork.date_end,
      };

		fetch("http://localhost:8080/api/dislike/save", {
			method: "PUT",
			headers: {
				"Content-Type": "application/json",
				"X-XSRF-TOKEN": token,
			},
			credentials: "include",
			body: JSON.stringify(dislikedArtwork),
		})
			.then((response) => {
				if (!response.ok) {
					throw new Error("Failed to save dislike");
				}
				return response.json();
			})
			.then((data) => {
				console.log("dislike saved successfully:", data);
			})
			.catch((error) => {
				console.error("Error saving dislike:", error);
			});
	};

	const outOfFrame = () => {
		setCurrentIndex((prevIndex) => {
			return prevIndex + 1;
		});
	};

	const handleMouseDown = (event) => {
		event.preventDefault();
	};

	const handleDragStart = (event) => {
		event.preventDefault();
	};

	return (
		<>
			<div>
				<div
					className="cardContainer"
					style={{ maxWidth: "30%" }}
					onMouseDown={handleMouseDown}
				>
					{artworks.map((artwork, index) => (
						<div
							key={artwork.id}
							style={{
								display: index === currentIndex ? "block" : "none",
							}}
						>
							<div className="tinderCardWrapper">
								<TinderCard
									className="swipe"
									onSwipe={(dir) => swiped(dir, artwork)}
									onCardLeftScreen={() => outOfFrame(artwork.id)}
									preventSwipe={["up", "down"]}
									swipeRequirementType="positon"
									swipeThreshold={100}
									onDragStart={handleDragStart}
								>
									<div className="card">
										<img
											className="artwork-image"
											src={imageSources[artwork.id]} //Accessing the value at the artwork ID key in the imageSources object
											alt={artwork.thumbnail?.alt_text}
											style={{
												maxWidth: "inherit",
												maxHeight: "inherit",
											}}
										/>
										<h2>{artwork.title}</h2>
										<p>{artwork.classification_title}</p>
									</div>
								</TinderCard>
							</div>
						</div>
					))}
				</div>
			</div>
		</>
	);
}

export default DisplayArtworks;
