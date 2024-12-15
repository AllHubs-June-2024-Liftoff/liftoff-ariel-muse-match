import { useEffect, useState } from 'react'
import './App.css'
import fetchArtworks from './components/match/FetchArtworks' 
import getImage from './components/image/GetImageId.jsx' 

function App() {
  const [artworks, setArtworks]= useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);


  useEffect(() => {
    const loadArtworks = async () => {
        try {
            const data = await fetchArtworks(); 
            if (data && data.data) {
              setArtworks(data.data);
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
                            src={getImage(artwork.image_id)}
                            alt={artwork.thumbnail.alt_text}
                            style={{ width: "300px" }}
                        />
                    </li>
                ))}
        </ul>
      </div>
    </>
  )
}

export default App

