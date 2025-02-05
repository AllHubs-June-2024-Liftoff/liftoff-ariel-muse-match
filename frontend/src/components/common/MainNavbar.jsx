import React from "react";
import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import NavDropdown from "react-bootstrap/NavDropdown";
import Button from "react-bootstrap/Button";
import { useAuth } from "../auth/AuthContext";

export default function MainNavbar() {
	const { isAuthenticated, logout } = useAuth();

	const handleLogout = async (e) => {
		e.preventDefault();
		logout();
	};

	return (
		<>
			<Navbar expand="lg" className="bg-body-tertiary">
				<Container className="nav-container">
					<Navbar.Brand href="/">MuseMatch</Navbar.Brand>
					<Navbar.Toggle aria-controls="basic-navbar-nav" />
					<Navbar.Collapse id="basic-navbar-nav">
						<Nav className="me-auto">
							<Nav.Link href="/">Home</Nav.Link>
							<Nav.Link href="/matches">Matches</Nav.Link>
							<Nav.Link href="/contacts">Contacts</Nav.Link>
							<Nav.Link href="/give">Give</Nav.Link>
							{isAuthenticated ? (
								<>
									<NavDropdown title="My Account" id="profile-dropdown">
										<NavDropdown.Item href="/my-profile">
											View profile
										</NavDropdown.Item>
										<NavDropdown.Item href="/stats">Stats</NavDropdown.Item>
										<NavDropdown.Divider />
										<NavDropdown.Item href="/settings">
											Settings
										</NavDropdown.Item>
									</NavDropdown>
									<Button
										variant="dark"
										type="submit"
										onClick={handleLogout}
									>
										Logout
									</Button>
								</>
							) : (
								<></>
							)}
						</Nav>
					</Navbar.Collapse>
				</Container>
			</Navbar>
		</>
	);
}
