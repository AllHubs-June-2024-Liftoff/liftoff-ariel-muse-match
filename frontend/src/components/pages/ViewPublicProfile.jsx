import React from "react";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import Card from "react-bootstrap/Card";
import ListGroup from "react-bootstrap/ListGroup";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import { Image } from "react-bootstrap";

import { useAuth } from "../auth/AuthContext";


export default function ViewPublicProfile() {
  const {username} = useParams();
  const [profile, setProfile] = useState(null);
  const [milestones, setMilestones] = useState(null);
  const [greatestLikeMilestone, setgreatestLikeMilestone] = useState(null);
  const [dummy, setDummy] = useState(null);
  const [error, setError] = useState('');
  const { getCsrfToken } = useAuth()
  const [loading, setLoading] = useState(true);
  const [isPrivate, setIsPrivate] = useState();


  // useEffect(() => {
  //   const fetchProfile = async () =>{

  //   }
  // });


  useEffect(() => {
    const fetchProfile = async () => {
      try {
        const token = await getCsrfToken();
        const response = await fetch(`http://localhost:8080/api/profile/${username}`, {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
            "X-CSRF-TOKEN": token,
          },
          credentials: "include"
        });

        if (response.ok) {
          const res = await response.json();
          console.log(res);
          setProfile(res);
          setLoading(false);
          setMilestones(res.milestones);
        
          if (res.milestones && res.milestones.length > 0) {
            console.log(res.milestones);
            const great = res.milestones.reduce(
              (max, milestone) => (milestone.achievement > max.achievement ? milestone : max),
              res.milestones[0] 
            );
            setgreatestLikeMilestone(great);
            console.log(great.type);
          }
        }else if (!response.ok){
          if(response.status == 403){
            setIsPrivate(true);
          }
          console.log(response)
          setLoading(false);
        }
      } catch (error) {
        console.error("Failed to fetch profile");
        setLoading(false);
      }
    };
    fetchProfile();
  }, [loading, username]);

  if (loading) return <div>Loading...</div>;

  return(
    profile && !loading ? (
    <>
    	<h1>{username}'s public profile</h1>
			<Row>
				<Col xs={3}>
					<Card style={{ marginTop: "50px", width: "18rem" }}>
						<Card.Img variant="top" src="/muse.png" />
						<Card.Body>
							<Card.Title>@{profile.username}</Card.Title>
							<Card.Text>{profile.bio}</Card.Text>
						</Card.Body>
						<ListGroup className="list-group-flush">
							<ListGroup.Item>Best Milestone: {greatestLikeMilestone.type}</ListGroup.Item>
							<ListGroup.Item>Milestone Value: {greatestLikeMilestone.achievement} likes</ListGroup.Item>
						</ListGroup>
					</Card>
				</Col>
				<Col>
          <ListGroup style={{ marginTop: "50px"} }>
            <h2 style={{textAlign:"center", marginBottom:"16px"}}>All Achievements</h2>
            {milestones.map((item, index) =>(
              <ListGroup.Item key={index}>
                Type: {item.type} 
              <p>Achieved date: {item.achievedDate} </p>
              Value: {item.achievement}
              </ListGroup.Item>
            ))}
          </ListGroup>
				</Col>
			</Row>
    </>
    ) : isPrivate ? <h1>Profile is private!</h1> : <h1>Profile doesn't exist!</h1>
  );
}