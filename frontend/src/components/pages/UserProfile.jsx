import React from "react";
import Form from 'react-bootstrap/Form';
import { useState, useEffect } from "react";
import { useAuth } from "../auth/AuthContext";
import Card from 'react-bootstrap/Card';
import ListGroup from 'react-bootstrap/ListGroup';


export default function UserProfile(params) {
  const {userName, email} = useAuth();

  //const [userName, setUserName] = useState("");
  const [myEmail, setMyEmail] = useState("");

  // useEffect(()=>{
  //   setUserName("John Doe");
  //   setMyEmail("john@placeholder.com")
  // }, []);

  
  return (
    <>
      <h1>Your Profile</h1>
      {/* <h4>@{userName}</h4>
      <h5>Email: {email}</h5> */}

      <Card style={{ marginTop:"30px", width: '18rem' }}>
      <Card.Img variant="top" src="muse.png" />
      <Card.Body>
        <Card.Title>@{userName}</Card.Title>
        <Card.Text>
          This is my sample bio. Check me out!
        </Card.Text>
      </Card.Body>
      <ListGroup className="list-group-flush">
        <ListGroup.Item>Login Streak: 5 days</ListGroup.Item>
        <ListGroup.Item>Like Milestone: 500 likes</ListGroup.Item>
        <ListGroup.Item>Match Milestone: 100 matches</ListGroup.Item>
      </ListGroup>
      {/* <Card.Body>
        <Card.Link href="#">Card Link</Card.Link>
        <Card.Link href="#">Another Link</Card.Link>
      </Card.Body> */}
    </Card>
    </>
  );
}