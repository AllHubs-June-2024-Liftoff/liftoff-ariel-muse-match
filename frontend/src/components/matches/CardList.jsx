import React, { useEffect, useState } from 'react';
import CustomCard from './CustomCard';

function CardList() {
  const [matches, setMatches] = useState([]);
  const [loading, setLoading] = useState(true);
  useEffect(() => {
          console.log("Got here: cardlist")

    const fetchMatches = async () => {
      try {
          console.log("got here #2");
          const userId = 1;
        const response = await fetch(`http://localhost:8080/api/matches/${userId}`, {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
          },
          credentials: "include",
        });
    console.log("got here #3");

        if (!response.ok) {
          throw new Error('Failed to fetch matches');
        }
        const data = await response.json();
        console.log("Fetched matches data:", data);
        console.log("got here #4");
        setMatches(data);
        setLoading(false);
        console.log(data);
        }
        catch (error){
            console.log("got here: #5");
        console.error('Error fetching matches:', error);
        setLoading(false);
        }

    }

    fetchMatches();
  }, []);

  if (loading) {
    return <div>Loading...</div>;
  }

  return (
    <div style={{ display: 'flex', flexWrap: 'wrap', gap: '16px' }}>
      {matches.map((match) => (
        <CustomCard
          artistId={match.artistId}
          artistName={match.artistTitle}
          artworkTypeTitle={match.artType}
          picture={match.imageId}
          placeOfOrigin={match.placeOfOrigin}
          styleTitle={match.artMovement}
        />
      ))}
    </div>
  );
}

export default CardList;