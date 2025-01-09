import React from "react";
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import Card from 'react-bootstrap/Card';
export default function Contact(params) {

  return(
    <>
      <h1>Contacts</h1>
      <div style={{marginTop: "20px"}}>
        <Card style={{ width: '18rem' }}>
          <Card.Img variant="top" src="muse.png" />
          <Card.Body>
            <Card.Title>Placeholder Contact</Card.Title>
            <Card.Text>
              Short description about the contact.
            </Card.Text>
            <Button variant="primary">View contact</Button>
          </Card.Body>
        </Card>
      </div>
    </>
  );
}