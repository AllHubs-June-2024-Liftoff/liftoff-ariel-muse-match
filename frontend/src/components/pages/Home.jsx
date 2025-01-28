import React from "react";
import { Image } from "react-bootstrap";
import Login from "./Login";
import { useState, useEffect } from "react";
import Button from "react-bootstrap/Button";
import DisplayArtworks from '../DisplayArtworks'
import { useAuth } from "../auth/AuthContext";



export default function Home(params) {

  const [loggedIn, setLoggedIn] = useState(false);
  const [data, setData] = useState('');
  const {isAuthenticated} = useAuth()

  // useEffect(() => {
  //   fetch('http://localhost:8080/api/hello')
  //   .then((response) =>{
  //     if(!response.ok) {
  //       throw new Error("Not ok")
  //     }
  //     return response.text();
  //   })
  //   .then((data) => setData(data))
  //   .catch((e)=> console.error("Couldn't fetch!"));
  // },[]);

  return (
    <div className="container mt-5">

      {!isAuthenticated ? 
        <>
          <div id="home-container">
            <h1>Welcome to MuseMatch</h1>
            <Image id="home-hero" className="w-25 mt-5 mb-5" style={{borderRadius: "6px"}} src="muse.png"/>
            <p id="home-description">MuseMatch is an art exploration app where you match with artists by swiping right on their art pieces.</p>
            <div id="auth-group">
              <Button className="auth-button" href="/login"> Login</Button>  <Button className="auth-button" href="sign-up"> Signup</Button>
            </div>
          </div>
        </>
      : 
        <>
          <h1 style={{marginBottom: "50px"}}>Find a Match </h1>
          <DisplayArtworks />
        </>
      }
      {/* <h1>{data}</h1> */}
      {/* <Image className="w-25 mt-5 mb-5" style={{borderRadius: "6px"}} src="muse.png"/>
      <p>MuseMatch is an art exploration app where you match with artists by swiping right on their art pieces.</p>
      {!loggedIn ? <><Button href="/login"> Login</Button>  <Button href="sign-up"> Signup</Button></>: <></> } */}
    </div>
  );
}