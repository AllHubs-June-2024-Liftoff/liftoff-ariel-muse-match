import React, { useEffect } from "react";
import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/Button";
import { useAuth } from "../auth/AuthContext";
import { useState } from "react";

export default function Reflection(match) {
	const { getCsrfToken } = useAuth();
	const [show, setShow] = useState(false);

	const handleClose = () => setShow(false);

	const handleDelete = async () => {
		const token = await getCsrfToken();
		await fetch(`http://localhost:8080/matches/delete/${match.matchId}`, {
			method: "DELETE",
			headers: {
				"Content-Type": "application/json",
				"X-XSRF-TOKEN": token,
			},
			credentials: "include",
		});
		setShow(false);
	};

	const handleSave = async () => {
		const token = await getCsrfToken();
		const reflection = document.getElementById("reflection").value;
		if (reflection != match.reflection && reflection.trim() != "") {
			await fetch(`http://localhost:8080/matches/save/${match.matchId}`, {
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

	const handleShow = () => {
		setShow(true);
	};

	return (
		<>
			<Button
				style={{ marginBottom: "10px" }}
				variant="primary"
				onClick={handleShow}
			>
				Reflection
			</Button>

			<Modal show={show} onHide={handleClose}>
				<Modal.Header closeButton>
					<Modal.Title>Leave a match reflection</Modal.Title>
				</Modal.Header>
				<Modal.Body>
					<p>{match.reflection}</p>
					<textarea
						id="reflection"
						name="reflection"
						rows="4"
						cols="50"
						placeholder="Enter your reflection here"
					></textarea>
				</Modal.Body>
				<Modal.Footer>
					<Button variant="secondary" onClick={handleClose}>
						Close
					</Button>
					<Button varieant="danger" onClick={handleDelete}>
						Delete Reflection
					</Button>
					<Button variant="primary" onClick={handleSave}>
						Save Changes
					</Button>
				</Modal.Footer>
			</Modal>
		</>
	);
}
