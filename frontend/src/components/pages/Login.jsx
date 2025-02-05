import React, {useState, useEffect} from "react";
import {useNavigate} from "react-router";
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import { useAuth } from "../auth/AuthContext";
export default function Login(params) {
  const {login, registerUser} = useAuth();
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    login(username, password);
    
  }

  const handleRegister = async (e) => {
    e.preventDefault();
    registerUser();
  };

  const handleChange = (e) => {
    const {name, value} = e.target;
    if (name === "username") setUsername(value);
    if (name === "password") setPassword(value); 
  }

  return (
    <>
      <h1>Login</h1>
      <Form onSubmit={handleLogin}>
        <Form.Group className="mb-3" controlId="formBasicEmail">
          <Form.Label>Username</Form.Label>
          <Form.Control required="true" type="text" name="username" placeholder="Enter username" value={username} onChange={(handleChange)} />
        </Form.Group>

        <Form.Group className="mb-3" controlId="formBasicPassword">
          <Form.Label>Password</Form.Label>
          <Form.Control  required="true" type="password" name="password" placeholder="Password" value={password} onChange={(handleChange)}/>
        </Form.Group>
        <Form.Group className="mb-3" controlId="formBasicCheckbox">
        </Form.Group>
        <Button variant="primary" type="submit">
          Submit
        </Button>
        <a href="/sign-up" style={{display:"block"}}>No account? Create one</a>
      </Form>
    </>
  );
}