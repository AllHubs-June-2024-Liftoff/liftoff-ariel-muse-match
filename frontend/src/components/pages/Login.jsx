import React from "react";
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
export default function Login(params) {

  return (
    <>
      <h1>Login</h1>
      <Form>
        <Form.Group className="mb-3" controlId="formBasicEmail">
          <Form.Label>Email address</Form.Label>
          <Form.Control type="email" placeholder="Enter email" />
        </Form.Group>

        <Form.Group className="mb-3" controlId="formBasicPassword">
          <Form.Label>Password</Form.Label>
          <Form.Control type="password" placeholder="Password" />
        </Form.Group>
        <Form.Group className="mb-3" controlId="formBasicCheckbox">
          <Form.Check type="checkbox" label="Check me out" />
        </Form.Group>
        <Button variant="primary" type="submit">
          Submit
        </Button>
        <a href="/sign-up" style={{display:"block"}}>No account? Create one</a>
      </Form>
    </>
  );
}