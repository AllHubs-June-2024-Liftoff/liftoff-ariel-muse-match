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
          <Image className="d-block w-100" text="First slide" src="https://npr.brightspotcdn.com/de/a1/b1cce2b74b0ba8c5c0ecdae20d15/060723-tr-launchcode.jpg"/>
          <Carousel.Caption>
            <h3>LaunchCode</h3>
            <p>LaunchCode is a non-profit organization that teaches individuals of any background how to code. </p>
            <p>Without LaunchCode, MuseMatch would not have been possible.  Please consider donating today!</p>
            <a href="https://www.launchcode.org/donate">Donate to LaunchCode</a>
          </Carousel.Caption>
        </Carousel.Item>
        <Carousel.Item>
          <Image className="d-block w-100" text="Second slide" src="https://www.terraamericanart.org/wp-content/uploads/2015/08/AIC-Facade-North-View.jpg"/>
          <Carousel.Caption>
            <h3>the Art Institute of Chicago</h3>
            <p>All of the artworks seen on MuseMatch are made available through the AI of Chicago</p>
            <p>MuseMatch was created to pay tribute and raise awareness of the wonderful resources like the Art Institute of Chicago provides to the public.</p>
            <p>Consider donating to the Art Institute today!</p>
            <a href="https://sales.artic.edu/donate">Donate to the Art Institute of Chicago</a>
          </Carousel.Caption>
        </Carousel.Item>
        <Carousel.Item>
          <Image className="d-block w-100" text="Third slide" src="muse.png"/>
          <Carousel.Caption>
            <h3>The Nelson Atkins Museum of Art</h3>
            <p>
              The Nelson Atkins Museum of Art is a wonderful museum located in Kansas City, Missouri, where two of the creators of MuseMatch are from.
            </p>
          </Carousel.Caption>
        </Carousel.Item>
      </Carousel>
    </>
  );
}