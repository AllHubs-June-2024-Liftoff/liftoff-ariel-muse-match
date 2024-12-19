import React, { useEffect, useState } from "react";

function Stats() {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetch("http://localhost:8080/profile/stats/test")
      .then((response) => response.json())
      .then((data) => {
        setData(data);
        setLoading(false);
      })
      .catch((error) => {
        console.error("Error Fetching Data: ", error);
        setLoading(false);
      });
  }, []);

  if (loading) {
    return <p>Loading...</p>;
  }

  return (
    <div>
      <p>
        {Object.keys(data)}: {data.Minimalist.total}
      </p>
    </div>
  );
}

export default Stats;
