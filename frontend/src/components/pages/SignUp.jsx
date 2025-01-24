import React from "react";
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
export default function SignUp(params) {

  return (
    <>
    <h1>Sign up</h1>
    <Form>

      <Form.Group className="mb-3" controlId="formBasicDisplayName">
        <Form.Label>Display Name</Form.Label>
        <Form.Control type="text" placeholder="Enter display name" />
      </Form.Group>

      <Form.Group className="mb-3" controlId="formBasicEmail">
        <Form.Label>Email address</Form.Label>
        <Form.Control type="email" placeholder="Enter email" />
        <Form.Text className="text-muted">
          We'll never share your email with anyone else.
        </Form.Text>
      </Form.Group>

      <Form.Group className="mb-3" controlId="formBasicPassword">
        <Form.Label>Password</Form.Label>
        <Form.Control type="password" placeholder="Password" />
      </Form.Group>
      <Form.Group className="mb-3" controlId="formBasicCheckbox">
        <Form.Check type="checkbox" label="I have read and agree to the Terms and Conditions" />
      </Form.Group>
      <Button variant="primary" type="submit">
        Submit
      </Button>
      <a href="/login" style={{display:"block"}}>Have an account? Sign in</a>
    </Form>
    </>
  );

}