import React from "react";
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import Carousel from 'react-bootstrap/Carousel';
import { Image } from "react-bootstrap";

export default function Give(params) {

  return(
    <>
      <h1 style={{marginBottom:"40px"}}>Give</h1>
      <Carousel>
        <Carousel.Item>
          <Image className="d-block w-100" text="First slide" src="muse.png"/>
          <Carousel.Caption>
            <h3>Donate to the Art Institute of Chicago</h3>
            <p>All of the art you've seen in MuseMatch has been sourced from the Art Institute of Chicago.</p>
            <p><a href="https://sales.artic.edu/donate">Gift today!</a></p>
          </Carousel.Caption>
        </Carousel.Item>
        <Carousel.Item>
          <Image className="d-block w-100" text="Second slide" src="muse.png"/>
          <Carousel.Caption>
            <h3>Donate to Launch Code!</h3>
            <p>Launch Code is a non-profit organization that helps people from non-traditional backgrounds start careers in tech.</p>
            <p>Witout LaunchCode, MuseMatch would not have been possible.  Please show your support to them today!</p>
            <p><a href="https://www.launchcode.org/donate">Gift today</a></p>
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