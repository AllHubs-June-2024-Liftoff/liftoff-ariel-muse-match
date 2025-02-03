import { useState } from "react";
import { Form } from "react-bootstrap";
import React from "react";
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import InputGroup from 'react-bootstrap/InputGroup';
import Button from "react-bootstrap/Button";
import { useAuth } from "../auth/AuthContext";

export default function MySettings(params) {
  const { getCsrfToken, isPublic } = useAuth() 
  const [bio, setBio] = useState('');
  const [isVisible, setIsVisible] = useState(isPublic);
  const [loading, setLoading] = useState(false);
  const [bioUpdated, setBioUpdated] = useState();
  
  

  const handleBioSubmit = async (e) => {
    e.preventDefault();
    const token = await getCsrfToken();
    const updateBio = async () => {
      try {
        const response = await fetch("http://localhost:8080/api/profile/update/bio", {
          method: "PUT",
          headers: {
						"Content-Type": "application/json",
						"X-XSRF-TOKEN": token,
					},
          credentials: "include",
          body: bio,
        });

        if(response.ok) {
          setLoading(false);
          setBioUpdated(true);
        }
      } catch (error) {
        console.error("Failed to update bio");
        console.error(error);
        setLoading(false);
      }
    }
    updateBio();
  };

  const handleProfileSwitch = async (e) => {
    e.preventDefault();
    const updateProfileVisibility = async () => {
      try {
        const token = await getCsrfToken();
        const newState = !isVisible;
        const response = await fetch("http://localhost:8080/api/profile/update/visibility", {
          method: "PUT",
          headers: {
						"Content-Type": "application/json",
						"X-XSRF-TOKEN": token,
					},
          credentials: "include",
          body: newState,
        });

        if(response.ok) {
          setIsVisible(newState)
          setLoading(false);
        }
      } catch (error) {
        console.error("Failed to update profile visibility");
        console.error(error);
        setLoading(false);
      }
    }
    updateProfileVisibility();
  };

  return (
    <>
        <h2>Settings</h2>
        <Row>
          <p>Edit Profile Visibility:</p>
          <Form>
            <Form.Check
              type="switch"
              id="custom-switch"
              label="Profile visible"
              checked={isVisible}
              onChange={handleProfileSwitch}
              disabled={loading}
            />
          </Form>
        </Row>



      <Form onSubmit={handleBioSubmit}>
        <Form.Label style={{marginTop:"10px"}} htmlFor="basic-url">{!bioUpdated ? "Edit your Bio": "Bio updated!"}</Form.Label>
        <InputGroup>
          <InputGroup.Text>Bio</InputGroup.Text>
          <Form.Control required={true} as="textarea" aria-label="With textarea"
           value={bio} onChange={(e) => setBio(e.target.value)} placeholder="Enter new bio..." />
        </InputGroup>
        <Button style={{marginTop: "15px"}} variant="primary" type="submit">
          Save
        </Button>
      </Form>
    </>
  );
}