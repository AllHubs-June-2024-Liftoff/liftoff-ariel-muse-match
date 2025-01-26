import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown'
import { useAuth } from '../auth/AuthContext';
import { Outlet } from "react-router-dom";


export default function MainNavbar() {
  const {isAuthenticated} = useAuth();
  return (
    <>
      <Navbar expand="lg" className="bg-body-tertiary" sticky="top">
      <Container>
        <Navbar.Brand href="/">MuseMatch</Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="me-auto">
            <Nav.Link href="/">Home</Nav.Link>
            <Nav.Link href="/match">Match</Nav.Link>
            <Nav.Link href="/contacts">Contacts</Nav.Link>
            <Nav.Link href="/give">Give</Nav.Link>
            {isAuthenticated ? <><NavDropdown title="My Account" id="profile-dropdown">
              <NavDropdown.Item href="/my-profile">View profile</NavDropdown.Item>
              <NavDropdown.Divider />
              <NavDropdown.Item href="/profile/settings">
                Settings
              </NavDropdown.Item>
               <NavDropdown.Item href="/profile/matches/">
                  Matches
              </NavDropdown.Item>
            </NavDropdown> </> : <></> }
          </Nav>
        </Navbar.Collapse>
      </Container>
      </Navbar>
    </>
  );
}