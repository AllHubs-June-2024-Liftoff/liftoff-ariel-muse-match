import React, {useState, useEffect} from "react";
import getImage from "./image/GetImage";
import fetchArtworks from "./match/FetchArtworks";



function DisplayArtworks() {

    const [artworks, setArtworks]= useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [imageSources, setImageSources] = useState({});
    
    useEffect(() => {

    const loadArtwork = async () => {
        try {
          const data = await fetchArtworks();
          if (data && data.data) {
            setArtworks(data.data);
    
            const sources = {}; //Key is artwork ID, Value is the returned URL from the getImage() function (link to image source)
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
      loadArtwork();
    }, []);
    
    
      if (loading) {
        return <div>Loading...</div>;
      }
    
      if (error) {
        return <div>Error: {error}</div>;
      }
    
      
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
                                src={imageSources[artwork.id]} //Returns value of the key artwork.id at given index position (key is artwork.id)
                                alt={artwork.thumbnail.alt_text}
                                style={{ width: "300px" }}
                            />
                        </li>
                    ))}
            </ul>
          </div>
        </>
      );
    
     }
    
      export default DisplayArtworks;