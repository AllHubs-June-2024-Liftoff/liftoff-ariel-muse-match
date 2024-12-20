import React, { useEffect, useState } from "react";

function Stats() {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [uri, setUri] = useState("general");

  useEffect(() => {
    fetch(`http://localhost:8080/profile/stats/${uri}`)
      .then((response) => response.json())
      .then((data) => {
        setData(data);
        setLoading(false);
      })
      .catch((error) => {
        console.error("Error Fetching Data: ", error);
        setLoading(false);
      });
  }, [uri]);

  if (loading) {
    return <p>Loading...</p>;
  }

  data.map((datum) => {
    const liked = data[datum].likes;
    const total = data[datum].total;
    const percent = data[datum].percent;
    return (
      <div>
        <h3>{datum}</h3>
        <p>
          Liked: {percent}% {`(${liked}/${total})`}
        </p>
      </div>
    );
  });
}

export default Stats;
