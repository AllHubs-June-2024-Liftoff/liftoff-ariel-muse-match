import React, { useEffect, useState } from 'react';
import Card from './Card';

function CardList() {
  const [matches, setMatches] = useState([]); // Store match data
  const [loading, setLoading] = useState(true); // Loading state

  // Fetch data from the API
  useEffect(() => {
    const fetchMatches = async () => {
      try {
        const response = await fetch('/api/matches'); // Replace with your API URL
        const data = await response.json();
        setMatches(data);
        setLoading(false);
      } catch (error) {
        console.error('Error fetching matches:', error);
        setLoading(false);
      }
    };

    fetchMatches();
  }, []);

  if (loading) {
    return <p>Loading...</p>; // Show a loading indicator while data is being fetched
  }

  return (
    <div style={{ display: 'flex', flexWrap: 'wrap', gap: '16px' }}>
      {matches.map((match) => (
        <Card
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