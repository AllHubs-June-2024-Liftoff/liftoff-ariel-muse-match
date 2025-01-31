import React from "react";
import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/Button";
import { useAuth } from "../auth/AuthContext";

export default function Reflection(match) {
	const { getCsrfToken } = useAuth();
	const [show, setShow] = useState(false);

	const handleClose = () => setShow(false);

	const handleSave = () => {
		const token = getCsrfToken();
		const reflection = document.getElementById("reflection").value;
		if (reflection != match.reflection && reflection.isNotEmpty()) {
			fetch(`http://localhost:8080/matches/save/${match.id}`, {
				method: "PUT",
				headers: {
					"Content-Type": "application/json",
					"X-XSRF-TOKEN": token,
				},
				credentials: "include",
				body: JSON.stringify({ reflection }),
			});
		}
		setShow(false);
	};

	const handleShow = () => setShow(true);

	return (
		<>
			<Button variant="primary" onClick={handleShow}>
				Launch demo modal
			</Button>

			<Modal show={show} onHide={handleClose}>
				<Modal.Header closeButton>
					<Modal.Title>Leave a match reflection</Modal.Title>
				</Modal.Header>
				<Modal.Body>
					<textarea
						id="reflection"
						name="reflection"
						rows="4"
						cols="50"
						placeholder="Enter your reflection here"
					>
						{match.reflection}
					</textarea>
				</Modal.Body>
				<Modal.Footer>
					<Button variant="secondary" onClick={handleClose}>
						Close
					</Button>
					<Button variant="primary" onClick={handleSave}>
						Save Changes
					</Button>
				</Modal.Footer>
			</Modal>
		</>
	);
}
