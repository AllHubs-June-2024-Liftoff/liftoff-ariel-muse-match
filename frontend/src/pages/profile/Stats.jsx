import React, { useEffect, useState } from "react";

function Stats() {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [uri, setUri] = useState(["type", "percentage"]);
  const [activeButton, setActiveButton] = useState(["button1", "button1"]);

  const changeCategoryPathAndActive = (path, activeButton) => {
    setUri[0](path);
    setActiveButton[0](activeButton);
  };

  const changeSortPathAndActive = (path, activeButton) => {
    setUri[1](path);
    setActiveButton[1](activeButton);
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
        <Button
          onClick={() => changeCategoryPathAndActive("type", "button1")}
          className={activeButton[0] === "button1" ? "active" : ""}
        >
          By Art Type
        </Button>
        <Button
          onClick={() => changeCategoryPathAndActive("movement", "button2")}
          className={activeButton[0] === "button2" ? "active" : ""}
        >
          By Art Movement
        </Button>
        <Button
          onClick={() => changeCategoryPathAndActive("artist", "button3")}
          className={activeButton[0] === "button3" ? "active" : ""}
        >
          By Artist
        </Button>
        <Button
          onClick={() => changeCategoryPathAndActive("year", "button4")}
          className={activeButton[0] === "button4" ? "active" : ""}
        >
          By Year
        </Button>
      </div>
      <div>
        <Button
          onClick={() => changeSortPathAndActive("percentage", "button1")}
          className={activeButton[1] === "button1" ? "active" : ""}
        >
          By Percentage
        </Button>
        <Button
          onClick={() => changeSortPathAndActive("total", "button2")}
          className={activeButton[1] === "button2" ? "active" : ""}
        >
          By Total
        </Button>
        <Button
          onClick={() => changeSortPathAndActive("Likes", "button3")}
          className={activeButton[1] === "button3" ? "active" : ""}
        >
          By Likes
        </Button>
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
