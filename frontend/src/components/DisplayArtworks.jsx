import React, {useState, useEffect} from "react";
import getImage from "./image/GetImage";
import fetchArtworks from "./match/FetchArtworks";
import "../App.css";
import TinderCard from "react-tinder-card";


function DisplayArtworks() {
  const [artworks, setArtworks] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [imageSources, setImageSources] = useState({});
  const [currentIndex, setCurrentIndex] = useState(0);
    
    useEffect(() => {

    const loadArtwork = async () => {
        try {
          const data = await fetchArtworks();
          if (data && data.data) {
            setArtworks(data.data); //This sets the artworks to the data from the fetchArtworks function
    
            const sources = {}; //Key is artwork ID, Value is the returned URL from the getImage() function (the image source)
            await Promise.all(
              data.data.map(async (artwork) => {
                if (artwork.image_id) {
                  try {
                    sources[artwork.id] = await getImage(artwork.image_id); //This is creating an object where every artwork Id is the key and the value is the image source (Link)
                    //{
                    //  123: "https://www.artic.edu/iiif/2/123/full/843,/0/default.jpg",} <---Creates this with each artwork ID
                  } catch (e) {
                    console.error(`Failed to fetch image for ${artwork.id}`, e); //If there is an error, the image source will be null
                    sources[artwork.id] = null;
                }
                }
              })
            );

            setImageSources(sources);
            setCurrentIndex(0); // Reset currentIndex when artworks are loaded (It started causing a bug for some reason?)
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
          console.log("You liked the artwork");
          sendLike(swipedArtwork);
        } else if (direction === "left" && swipedArtwork) {
          console.log("You disliked the artwork");
          sendDislike(swipedArtwork);

        }
        setCurrentIndex((prevIndex) => prevIndex + 1);
      };


      //Logic for sending a like to the backend
      const sendLike = (artwork) => {
        if (!artwork) return;


        const likedArtwork = {
          artworkId: artwork.id,
          artworkTitle: artwork.title,
          altText: artwork.thumbnail?.alt_text,
          placeOfOrigin: artwork.place_of_origin,
          description: artwork.description,
          artworkTypeTitle: artwork.artwork_type_title,
          artistId: artwork.artist_id,
          artistTitle: artwork.artist_title,
          styleTitle: artwork.style_title,
          ImageId: artwork.image_id,
        };

        console.log(JSON.stringify(likedArtwork));
        fetch("http://localhost:8080/api/like/save", {
          method: "PUT",
          headers: {
            "Content-Type": "application/json",
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
      }

      //Logic for sending a dislike to the backend
      const sendDislike = (artwork) => {
        if (!artwork) return;

        const dislikedArtwork = {
          artworkId: artwork.id,
          artworkTitle: artwork.title,
          altText: artwork.thumbnail?.alt_text,
          placeOfOrigin: artwork.place_of_origin,
          description: artwork.description,
          artworkTypeTitle: artwork.artwork_type_title,
          artistId: artwork.artist_id, 
          artistTitle: artwork.artist_title,
          styleTitle: artwork.style_title,
          ImageId: artwork.image_id,
      };

      fetch("http://localhost:8080/api/dislike/save", {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
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
      }

      const outOfFrame = (artworkId) => {
        console.log(`${artworkId} left the screen`);
        setCurrentIndex((prevIndex) => prevIndex + 1);
      };
    
    
      //TODO: Fix issue with scrolling causing art to change sometimes (wrap Tinder card component in a div with overflow:hidden, height: 100vh or something)
      //TODO: Createt artwrok-wrapper .CSS, and perhaps a .page-content css class for everything else (?)
      return (
        <>
          <div>
            <div className="cardContainer">
              {artworks.map((artwork, index) => (
                <div
                  key={artwork.id}
                  style={{ display: index === currentIndex ? "block" : "none" }}
                  >
                <TinderCard 
                className="swipe"
                onSwipe={(dir) => swiped(dir, artwork)}
                onCardLeftScreen={() => outOfFrame(artwork.id)}
                preventSwipe={["up", "down"]}
                swipeRequirementType="position"
                swipeThreshold={10}
            >
                 <div className="card">
                  <h2>{artwork.title}</h2>
                  <p>Title: {artwork.classification_title}</p>
                  <img
                    src={imageSources[artwork.id]} //Accessing the value at the artwork ID key in the imageSources object
                    alt={artwork.thumbnail?.alt_text} 
                  />
                </div>
                </TinderCard>
                </div>
              ))}
            </div>
          </div>
        </>
      );
      }
    
      export default DisplayArtworks;