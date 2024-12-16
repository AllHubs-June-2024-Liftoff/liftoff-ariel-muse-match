import React from "react";
import { useState } from "react";
import TinderCard from "react-tinder-card";



const [artworks, setArtworks]= useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [imageSources, setImageSources] = useState({});


  useEffect(() => {
    const loadArtworks = async () => {
      try {
        const data = await fetchArtworks();
        if (data && data.data) {
          setArtworks(data.data);

          const sources = {};
          await Promise.all(
            data.data.map(async (artwork) => {
              if (artwork.image_id) {
                try {
                  sources[artwork.id] = await getImage(artwork.image_id);
                } catch (e) {
                  console.error(`Failed to fetch image for ${artwork.id}`, e);
                  sources[artwork.id] = null; 
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

    loadArtworks();
  }, []);

if (loading) {
  return <p>Loading artworks...</p>;
}

if (error) {
  return <p>Error: {error}</p>;
}

// const handleSwipe = (direction) => {
//     console.log("you swiped:" + direction)


// }

  return (
    <>
      <div>
        <h1>
          Test Artworks
        </h1>
        <ul>
          {artworks.map((artwork) => (
                    <li key={artwork.id}>
                        <h2>{artwork.classification_title}</h2>
                        <p>Artist: {artwork.artist_title}</p>
                        <img
                            src={imageSources[artwork.id]}
                            alt={artwork.thumbnail.alt_text}
                            style={{ width: "300px" }}
                        />
                    </li>
                ))}
        </ul>
      </div>
    </>
  )