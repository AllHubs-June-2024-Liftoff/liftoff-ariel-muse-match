import React from "react";
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import Carousel from 'react-bootstrap/Carousel';
import { Image } from "react-bootstrap";

export default function Give(params) {

  return(
    <>
      <h1>Give</h1>
      <Carousel>
        <Carousel.Item>
          <Image className="d-block w-100" text="First slide" src="muse.png"/>
          <Carousel.Caption>
            <h3>First slide label</h3>
            <p>Gift today!</p>
          </Carousel.Caption>
        </Carousel.Item>
        <Carousel.Item>
          <Image className="d-block w-100" text="Second slide" src="muse.png"/>
          <Carousel.Caption>
            <h3>Second slide label</h3>
            <p>Gift today</p>
          </Carousel.Caption>
        </Carousel.Item>
        <Carousel.Item>
          <Image className="d-block w-100" text="Third slide" src="muse.png"/>
          <Carousel.Caption>
            <h3>Third slide label</h3>
            <p>
              Gift today!
            </p>
          </Carousel.Caption>
        </Carousel.Item>
      </Carousel>
    </>
  );
}