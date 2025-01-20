import React, { useEffect, useState } from 'react';
import CustomCard from './CustomCard';

function CardList() {
  const [matches, setMatches] = useState([]); // Store match data
  const [loading, setLoading] = useState(true); // Loading state

  // Fetch data from the API
  useEffect(() => {
    const fetchMatches = async () => {
      try {
        const response = await fetch('http://localhost:8080/api/matches');
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
    return <p>Loading...</p>;
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
