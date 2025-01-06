import React from "react";
import { Image } from "react-bootstrap";
import Login from "./Login";
import { useState, useEffect } from "react";
import Button from "react-bootstrap/Button";



export default function Home(params) {

  const [loggedIn, setLoggedIn] = useState(false);
  const [data, setData] = useState('');

  useEffect(() => {
    fetch('http://localhost:8080/api/hello')
    .then((response) =>{
      if(!response.ok) {
        throw new Error("Not ok")
      }
      return response.text();
    })
    .then((data) => setData(data))
    .catch((e)=> console.error("Couldn't fetch!"));
  },[]);

  return (
    <div className="container mt-5">
      <h1>{data}</h1>
      <Image className="w-25 mt-5 mb-5" style={{borderRadius: "6px"}} src="muse.png"/>
      <p>MuseMatch is an art exploration app where you match with artists by swiping right on their art pieces.</p>
      {!loggedIn ? <><Button href="/login"> Login</Button>  <Button href="sign-up"> Signup</Button></>: <></> }
    </div>
  );
}