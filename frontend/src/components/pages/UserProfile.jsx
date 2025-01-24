import React from "react";
import Form from "react-bootstrap/Form";
import { useState, useEffect } from "react";
import Stats from "./Stats";

export default function UserProfile(params) {
    const [userName, setUserName] = useState("");
    const [myEmail, setMyEmail] = useState("");

    useEffect(() => {
        setUserName("John Doe");
        setMyEmail("john@placeholder.com");
    }, []);

    return (
        <>
            <h4>@{userName}</h4>
            <h5>Email: {myEmail}</h5>
            <Stats />
        </>
    );
}
