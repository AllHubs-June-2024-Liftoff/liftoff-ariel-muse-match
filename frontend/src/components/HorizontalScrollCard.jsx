import React from 'react';
import "../HorizontalScroll.css";
import Card from './Card';

function HorizontalScroll(props) {

    const data = [
      { title: "Card 1", description: "This is the first card", imageUrl: "../src/assets/static/gustavdemon.jpg", imageAlt: "gustav dore", content: "Match 1" },
      { title: "Card 2", description: "This is the second card", imageUrl: "../src/assets/static/gustavdemon.jpg", imageAlt: "gustav dore", content: "Match 2" },
      { title: "Card 3", description: "This is the third card", imageUrl: "../src/assets/static/gustavdemon.jpg", imageAlt: "gustav dore", content: "Match 3" },
    ];

  return (
      <div className="horizontal-scroll-container">
        {props.children}
        {data.map((item, index) => (
          <Card
            key={index}
            title={item.title}
            description={item.description}
            imageUrl={item.imageUrl}
            imageAlt={item.imageAlt}
            content={item.content}
          />
        ))}
      </div>
  );
}

export default HorizontalScroll;
