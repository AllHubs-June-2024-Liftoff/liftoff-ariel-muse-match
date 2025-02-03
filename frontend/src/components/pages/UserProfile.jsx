import React from "react";
import Form from "react-bootstrap/Form";
import { useState, useEffect } from "react";
import { useAuth } from "../auth/AuthContext";
import Card from "react-bootstrap/Card";
import ListGroup from "react-bootstrap/ListGroup";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Accordion from "react-bootstrap/Accordion";
import Stats from "./Stats";


export default function UserProfile(params) {
	const { userName, bio, getCsrfToken } = useAuth()
  const [loading, setLoading] = useState(true);
  const [data, setData] = useState([]);
  const [greatestLikeMilestone, setgreatestLikeMilestone] = useState([]);
  const [milestoneName, setMilestoneName] = useState([]);
  const [allMilstones, setAllMilestones] = useState();




  


	// useEffect(()=>{
	//   setUserName("John Doe");
	//   setMyEmail("john@placeholder.com")
	// }, []);

  useEffect(() => {
    const fetchMilestones = async () => {
      try {
        const token = await getCsrfToken();
        const response = await fetch("http://localhost:8080/api/profile/milestone/all", {
          method: "GET",
          headers: {
						"Content-Type": "application/json",
						"X-XSRF-TOKEN": token,
					},
          credentials: "include"
        });

        if(response.ok) {
          const res = await response.json();
          setAllMilestones(res);
          setData(res);
          console.log(res)
          setLoading(false);
        }
      } catch (error) {
        console.error("Failed to fetch milestones");
        setLoading(false);
      }
    };
    fetchMilestones();
    if (!loading) {
      const great = data.reduce((max, milestone) => (milestone.achievement> max.achievement ? milestone: max));
      setgreatestLikeMilestone(great.achievement);
      setMilestoneName(great.type)
    }
  }, [loading]);
  

	return (
		<>
			<h1>Your Profile</h1>
      
			<Row>
				<Col xs={3}>
					<Card style={{ marginTop: "50px", width: "18rem" }}>
						<Card.Img variant="top" src="/muse.png" />
						<Card.Body>
							<Card.Title>@{userName}</Card.Title>
							<Card.Text>{bio}</Card.Text>
						</Card.Body>
						<ListGroup className="list-group-flush">
							<ListGroup.Item>Best Milestone: {milestoneName}</ListGroup.Item>
							<ListGroup.Item>Milestone Value: {greatestLikeMilestone} likes</ListGroup.Item>
						</ListGroup>
					</Card>
				</Col>
				<Col>
          <ListGroup style={{ marginTop: "50px"} }>
            <h2 style={{textAlign:"center", marginBottom:"16px"}}>All Achievements</h2>
            {data.map((item, index) =>(
              <ListGroup.Item key={index}>
                Type: {item.type} 
              <p>Achieved date: {item.achievedDate}</p>
              </ListGroup.Item>
            ))}
          </ListGroup>
				</Col>
			</Row>
			<Stats />
		</>
	);
}
