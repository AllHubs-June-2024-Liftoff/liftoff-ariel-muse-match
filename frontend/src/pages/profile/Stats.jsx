import React, { useEffect, useState } from "react";

function Stats() {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [uri, setUri] = useState(["artist", "percentage"]);

  const changeCategoryUri = (path) => {
    setUri[0](path);
  };

  const changeSortUri = (path) => {
    setUri[1](path);
  };

  useEffect(() => {
    setLoading(true);
    fetch(`http://localhost:8080/profile/stats/${uri[0]}/${uri[1]}`)
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
        <Button onClick={changeCategoryUri("type")}>By Art Type</Button>
        <Button onClick={changeCategoryUri("movement")}>By Art Movement</Button>
        <Button onClick={changeCategoryUri("artist")}>By Artist</Button>
        <Button onClick={changeCategoryUri("year")}>By Year</Button>
      </div>
      <div>
        <Button onClick={changeSortUri("percentage")}>By Percentage</Button>
        <Button onClick={changeSortUri("total")}>By Total</Button>
        <Button onClick={changeSortUri("Likes")}>By Likes</Button>
      </div>
      {data.map((datum) => {
        const liked = data[datum].statistics.likes;
        const total = data[datum].statistics.total;
        const percent = data[datum].statistics.percent;
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
