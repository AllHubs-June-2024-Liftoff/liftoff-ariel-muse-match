// import React from "react";

// //This service handles all API calls related toliked artworks

// //TODO: Implement the like and dislikes Artwork Service


// const ArtworkLikeService = {
//     //Save a liked artwork to the backend

//     saveLikedArtwork: async (artworkId, userId) => {
//         const response = await fetch("/api/like", {
//             method: "POST",
//             headers: {
//                 "Content-Type": "application/json",
//             },
//             body: JSON.stringify({ artworkId, userId })
//         });
//         if (!response.ok) {
//             throw new Error("Failed to save like");
//         }
//     }
// }