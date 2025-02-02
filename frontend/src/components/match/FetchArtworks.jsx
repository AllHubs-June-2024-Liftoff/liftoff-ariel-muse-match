import React from "react";

const fetchArtworks = async () => {
    try {
       
        const response = await fetch("http://localhost:8080/api/match/all", {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
          },
          credentials: "include", 
        })

        if (!response.ok) {
            throw new Error(`There was an error fetching your data: Status: ${response.status}`)
        } 
        const data = await response.json();
        //Check to see if response is filtered by liked_artworks
        console.log(data.data);
        if (!data.data) {
          //filtered data
          console.log("!if", data.imageId)
          return data
        }
        //unfiltered data
        console.log(data.data);
        return data.data;
        } catch (error) {
            console.error("Error fetching artworks:", error);
            throw error;
        }
    }

    export default fetchArtworks;