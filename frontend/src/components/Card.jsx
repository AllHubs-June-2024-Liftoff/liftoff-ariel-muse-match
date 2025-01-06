import React from "react";
import "../Card.css";

function Card(props) {
  return (
    <div className="card">
      <h2>{props.title}</h2>
      <img src={props.imageUrl} alt={props.imageAlt} />
      <p>{props.content}</p>
    </div>
  );
}
export default Card;