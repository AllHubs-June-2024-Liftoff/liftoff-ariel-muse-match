import React, {useState, useEffect} from "react";
import {useNavigate} from "react-router";
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import { useAuth } from "../auth/AuthContext";
export default function Login(params) {
  const {login} = useAuth();
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState(null);
    const [data, setData] = useState('');
  
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    login(username, password);
    
  }

  const handleChange = (e) => {
    const {name, value} = e.target;
    setUsername(username);
    if (name === "username") setUsername(value);
    if (name === "password") setPassword(value); 
    console.log(name, value); // Log the input'
  }

  return (
    <>
      <h1>Login</h1>
      <Form onSubmit={handleLogin}>
        <Form.Group className="mb-3" controlId="formBasicEmail">
          <Form.Label>Username</Form.Label>
          <Form.Control type="text" name="username" placeholder="Enter username" value={username} onChange={(handleChange)} />
        </Form.Group>

        <Form.Group className="mb-3" controlId="formBasicPassword">
          <Form.Label>Password</Form.Label>
          <Form.Control type="password" name="password" placeholder="Password" value={password} onChange={(handleChange)}/>
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