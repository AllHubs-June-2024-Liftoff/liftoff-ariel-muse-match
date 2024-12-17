import React, { useState } from "react";

const ArtistCard = ({ artist, onLike, onDislike }) => {
    const [isDragging, setIsDragging] = useState(false);

    const handleDragStart = () => setIsDragging(true);
    const handleDragEnd = () => setIsDragging(flase);

    const handleLike = () => {
        onLike(artist.id);
        // Send POST request to back end to save the like
        fetch("/api/like", {
            method: "POST",
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ artistId: artist.id, liked: true })
        })
            .then((response) => {
                if (!response.ok) {
                    throw new Error('Failed to save like');
                }
                return response.json();
            });
        };

        const handleDislike = () => {
            onDislike(artist.id);
            // This one sends the dislike to the back end
            fetch("/api/dislike", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ artistId: artist.id, liked: false})
            })
            .then((response) => {
                if (!response.ok) {
                    throw new Error("Failed to save dislike");
                }
            })
        }
}