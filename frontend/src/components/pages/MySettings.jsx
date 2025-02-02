import { useState, useContext } from "react";
import { Form } from "react-bootstrap";
import React from "react";
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import InputGroup from 'react-bootstrap/InputGroup';
import Button from "react-bootstrap/Button";
import { useIsLight } from "../Themes.jsx";


export default function MySettings(params) {
  const { isLight, toggleTheme } = useIsLight();


  return (
    <>
        <h2>Settings</h2>
        <Row>
          <p>Edit Profile Visibility:</p>
          <Form>
            <Form.Check
              type="switch"
              id="custom-switch"
              label="Public"
            />
            <Form.Check
              disabled
              type="switch"
              label="disabled switch"
              id="disabled-custom-switch"
            />
          </Form>

        </Row>
        <p style={{marginTop:"10px"}}>Edit Profile Details:</p>
        <InputGroup className="mb-3">
        <InputGroup.Text id="basic-addon1">@</InputGroup.Text>
        <Form.Control
          placeholder="Username"
          aria-label="Username"
          aria-describedby="basic-addon1"
        />
      </InputGroup>

      <InputGroup className="mb-3">
        <Form.Control
          placeholder="Your email"
          aria-label="Recipient's username"
          aria-describedby="basic-addon2"
        />
        <InputGroup.Text id="basic-addon2">@example.com</InputGroup.Text>
      </InputGroup>

      <Form.Label htmlFor="basic-url">Your profile URL</Form.Label>
      <InputGroup className="mb-3">
        <InputGroup.Text id="basic-addon3">
          https://example.com/users/
        </InputGroup.Text>
        <Form.Control id="basic-url" aria-describedby="basic-addon3" />
      </InputGroup>

      <Form.Label htmlFor="basic-url">Edit your Bio</Form.Label>
      <InputGroup>
        <InputGroup.Text>Bio</InputGroup.Text>
        <Form.Control as="textarea" aria-label="With textarea" />
      </InputGroup>

      <Form.Group className ="mb-3">
        <Form.Check
          type="switch"
          id="theme-switch"
          label="Dark Mode"
          checked={!isLight}
          onChange={toggleTheme}
        />
      </Form.Group>

      <Button style={{marginTop: "15px"}} variant="primary" type="submit">
        Save
      </Button>
    </>
  );
}