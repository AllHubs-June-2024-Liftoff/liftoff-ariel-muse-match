import React, { useEffect, useState } from 'react';
import CustomCard from './CustomCard';

function CardList() {
  const [matches, setMatches] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchMatches = async () => {
      try {
        const response = await fetch("http://localhost:8080/api/matches", {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
          },
          credentials: "include", // Includes cookies if any for authentication
        });

        if (!response.ok) {
          throw new Error('Failed to fetch matches');
        }

        const data = await response.json();
        console.log("Fetched matches data:", data);
        setMatches(data); // Update the matches state
        setLoading(false); // Turn off the loading state
      } catch (error) {
        console.error('Error fetching matches:', error);
        setLoading(false); // Turn off loading in case of error
      }
    };

    fetchMatches(); // Call the function to fetch data
  }, []); // The empty dependency array ensures the effect runs only once after the component mounts

  if (loading) {
    return <div>Loading...</div>; // You can display a loading indicator if the data is still being fetched
  }

  return (
    <div style={{ display: 'flex', flexWrap: 'wrap', gap: '16px' }}>
      {matches.map((match) => (
        <CustomCard
          key={match.id}
          id={match.id}
          artist={match.artist}
          artistName={match.artistName}
          picture={match.picture}
        />
      ))}
    </div>
  );
}

export default CardList;
