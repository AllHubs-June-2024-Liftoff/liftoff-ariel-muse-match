import React, {useState} from "react";
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import { useAuth } from "../auth/AuthContext";
export default function SignUp(params) {
  const {login, registerUser} = useAuth();
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [email, setEmail] = useState("");


  const handleRegister = async (e) => {
    e.preventDefault();
    registerUser(username, password, email);
  };

  const handleChange = (e) => {
    const {name, value} = e.target;
    if (name === "username") setUsername(value);
    if (name === "password") setPassword(value); 
    if (name === "email") setEmail(value); 
  }

  return (
    <>
    <h1>Sign up</h1>

    <Form onSubmit={handleRegister}>
      <Form.Group className="mb-3" controlId="formBasicDisplayName">
        <Form.Label>Username</Form.Label>
        <Form.Control type="text" name="username" placeholder="Enter username" onChange={handleChange} />
      </Form.Group>

      <Form.Group className="mb-3" controlId="formBasicEmail">
        <Form.Label>Email address</Form.Label>
        <Form.Control type="email" name="email" placeholder="Enter email" onChange={handleChange}/>
        <Form.Text className="text-muted">
          We'll never share your email with anyone else.
        </Form.Text>
      </Form.Group>

      <Form.Group className="mb-3" controlId="formBasicPassword">
        <Form.Label>Password</Form.Label>
        <Form.Control type="password" name="password" placeholder="Password" onChange={handleChange} />
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