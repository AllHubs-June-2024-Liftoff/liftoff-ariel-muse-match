import React from "react";
import Form from 'react-bootstrap/Form';
import { useState, useEffect } from "react";
import { useAuth } from "../auth/AuthContext";
import Card from 'react-bootstrap/Card';
import ListGroup from 'react-bootstrap/ListGroup';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Accordion from 'react-bootstrap/Accordion';




export default function UserProfile(params) {
  const {userName, email} = useAuth();




  
  return (
    <>
      <h1>Your Profile</h1>
    <h4>@{userName}</h4>
    <h5>Email: {email}</h5>
      <Row>
        <Col xs={3}>
          <Card style={{ marginTop:"50px", width: '18rem' }}>
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
        </Col>
        <Col>
          <Accordion defaultActiveKey="0" style={{marginTop:"50px"}}>
            <Accordion.Item eventKey="0">
              <Accordion.Header>Accordion Item #1</Accordion.Header>
              <Accordion.Body>
                Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
                eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad
                minim veniam, quis nostrud exercitation ullamco laboris nisi ut
                aliquip ex ea commodo consequat. Duis aute irure dolor in
                reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla
                pariatur. Excepteur sint occaecat cupidatat non proident, sunt in
                culpa qui officia deserunt mollit anim id est laborum.
              </Accordion.Body>
            </Accordion.Item>
            <Accordion.Item eventKey="1">
              <Accordion.Header>Accordion Item #2</Accordion.Header>
              <Accordion.Body>
                Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
                eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad
                minim veniam, quis nostrud exercitation ullamco laboris nisi ut
                aliquip ex ea commodo consequat. Duis aute irure dolor in
                reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla
                pariatur. Excepteur sint occaecat cupidatat non proident, sunt in
                culpa qui officia deserunt mollit anim id est laborum.
              </Accordion.Body>
            </Accordion.Item>
          </Accordion>
        </Col>
      </Row>
    </>
  );
}