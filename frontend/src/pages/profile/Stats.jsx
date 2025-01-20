import React, { useEffect, useState } from "react";

function Stats() {
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [uri, setUri] = useState({ category: "type", sort: "percentage" });
  const [activeButton, setActiveButton] = useState({
    set1: "button1",
    set2: "button1",
  });

  const changeCategoryPathAndActive = (path, active) => {
    setUri({ ...uri, category: path });
    setActiveButton({ ...activeButton, set1: active });
  };

  const changeSortPathAndActive = (path, active) => {
    setUri({ ...uri, sort: path });
    setActiveButton({ ...activeButton, set2: active });
  };

  useEffect(() => {
    setLoading(true);
    setLoading(false);
    fetch(`http://localhost:8080/profile/stats/${uri.category}/${uri.sort}`)
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

  console.log(data);

  if (loading) {
    return <p>Loading...</p>;
  }

  if (data == null) {
    return (
      <>
        <div>
          <button
            onClick={() => changeCategoryPathAndActive("type", "button1")}
            className={activeButton.set1 === "button1" ? "active" : ""}
          >
            By Art Type
          </button>
          <button
            onClick={() => changeCategoryPathAndActive("movement", "button2")}
            className={activeButton.set1 === "button2" ? "active" : ""}
          >
            By Art Movement
          </button>
          <button
            onClick={() => changeCategoryPathAndActive("artist", "button3")}
            className={activeButton.set1 === "button3" ? "active" : ""}
          >
            By Artist
          </button>
          <button
            onClick={() => changeCategoryPathAndActive("year", "button4")}
            className={activeButton.set1 === "button4" ? "active" : ""}
          >
            By Year
          </button>
        </div>
        <div>
          <button
            onClick={() => changeSortPathAndActive("percentage", "button1")}
            className={activeButton.set2 === "button1" ? "active" : ""}
          >
            By Percentage
          </button>
          <button
            onClick={() => changeSortPathAndActive("total", "button2")}
            className={activeButton.set2 === "button2" ? "active" : ""}
          >
            By Total
          </button>
          <button
            onClick={() => changeSortPathAndActive("likes", "button3")}
            className={activeButton.set2 === "button3" ? "active" : ""}
          >
            By Likes
          </button>
        </div>{" "}
        <p> No data to report</p>
      </>
    );
  }

  return (
    <>
      <div>
        <button
          onClick={() => changeCategoryPathAndActive("type", "button1")}
          className={activeButton.set1 === "button1" ? "active" : ""}
        >
          By Art Type
        </button>
        <button
          onClick={() => changeCategoryPathAndActive("movement", "button2")}
          className={activeButton.set1 === "button2" ? "active" : ""}
        >
          By Art Movement
        </button>
        <button
          onClick={() => changeCategoryPathAndActive("artist", "button3")}
          className={activeButton.set1 === "button3" ? "active" : ""}
        >
          By Artist
        </button>
        <button
          onClick={() => changeCategoryPathAndActive("year", "button4")}
          className={activeButton.set1 === "button4" ? "active" : ""}
        >
          By Year
        </button>
      </div>
      <div>
        <button
          onClick={() => changeSortPathAndActive("percentage", "button1")}
          className={activeButton.set2 === "button1" ? "active" : ""}
        >
          By Percentage
        </button>
        <button
          onClick={() => changeSortPathAndActive("total", "button2")}
          className={activeButton.set2 === "button2" ? "active" : ""}
        >
          By Total
        </button>
        <button
          onClick={() => changeSortPathAndActive("likes", "button3")}
          className={activeButton.set2 === "button3" ? "active" : ""}
        >
          By Likes
        </button>
      </div>
      {data.map((datum) => {
        const liked = datum.statistics.likes;
        const total = datum.statistics.total;
        const percentage = datum.statistics.percentage;
        return (
          <>
            <h3>{datum.info}</h3>
            <p>
              Liked: {`${percentage}`}% {`(${liked}/${total})`}
            </p>
          </>
        );
      })}
    </>
  );
}

export default Stats;
