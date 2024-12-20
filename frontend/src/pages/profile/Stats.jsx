import React, { useEffect, useState } from "react";

function Stats() {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [uri, setUri] = useState("general");

  const changeUri = (endPath) => {
    setUri(endPath);
  };

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

  return (
    <>
      <div>
        <button onClick={changeUri("type")}>By Art Type</button>
        <button onClick={changeUri("movement")}>By Art Movement</button>
        <button onClick={changeUri("artist")}>By Artist</button>
        <button onClick={changeUri("year")}>By Year</button>
      </div>
      {data.map((datum) => {
        const liked = data[datum].likes;
        const total = data[datum].total;
        const percent = data[datum].percent;
        return (
          <>
            <h3>{datum}</h3>
            <p>
              Liked: {percent}% {`(${liked}/${total})`}
            </p>
          </>
        );
      })}
    </>
  );
}

export default Stats;
