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
          console.log(response.statusText);
          console.log("got here 4");
        }
        const data = await response.json();
        console.log("Fetched matches data:", data);
        console.log("got here #5");
        setMatches(data);
        setLoading(false);}
        catch (error){
            console.log("got here: #6");
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
