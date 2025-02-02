import Button from 'react-bootstrap/Button';
import { Link } from 'react-router-dom';
import Card from 'react-bootstrap/Card';
import Dropper from './Dropper.jsx';
import getImage from '../image/GetImage.jsx';
import React, { useRef, useEffect, useState } from "react";
import Reflection from "./Reflection.jsx";
import { ButtonGroup } from "react-bootstrap";
import '../../styles/App.css';

export default function CustomCard(match) {
 const [imageUrl, setImageUrl] = useState(null);
 const [loading, setLoading] = useState(true);
 const [error, setError] = useState(false);

  useEffect(() => {
    console.log('This is a matched.picture' + match.imageId);
    console.log("This is a matched artist Id " + match.artistId);

    const fetchImage = async() => {
      const fetchedImageUrl = await getImage(match.imageId);
      console.log("Fetched image URL:", fetchedImageUrl);
      setImageUrl(fetchedImageUrl);
      setLoading(false);
    };
    fetchImage(match.imageId);

  }, [match.imageId]); //

   if (loading) {
       setTimeout(() => {
         setError(true);
         setLoading(false);

           }, 5000);
       if(loading){
           return (
              <Card style={{ width: '18rem' }}>
                <Card.Body>
                  <Card.Text>Loading image...</Card.Text>
                </Card.Body>
              </Card>
            );
       }


    }
  return (
    <Card style={{ width: '18rem' }}>
        <Card.Title>
            <Link
            className="text-decoration-none"
            to={`https://www.artic.edu/artists/${match.artistId}`}
            onClick={(e) => {
                e.preventDefault();
                window.open(e.target.href, '_blank', 'width=800,height=600');}}
                >
            {match.artistName || "Could not find artist"}
            </Link>
            </Card.Title>
  <Card.Img
    style={{
      objectFit: 'contain',
      height: '200px',
      width: '100%'
    }}
    variant="top"
    src={imageUrl}
    alt={(error) ? "Image Not Found " : "No image"}
  />
      <Card.Body>
        <Card.Subtitle>{(!match.styleTitle) ? null : match.styleTitle}</Card.Subtitle>
        <Card.Subtitle>{match.artType || "Art type Not Found"}</Card.Subtitle>
        <Card.Subtitle>{match.placeOfOrigin || "Origins Not Found"}</Card.Subtitle>
        <Card.Text></Card.Text>
        <ButtonGroup>
            <Reflection matchId={match.matchId} reflection={match.reflection} />
        <Dropper
        imageUrl = {imageUrl}
        artistName = {match.artistName}
        />
        </ButtonGroup>
      </Card.Body>
    </Card>
  );
}

