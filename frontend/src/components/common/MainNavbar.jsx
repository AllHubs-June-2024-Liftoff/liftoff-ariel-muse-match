import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown'


export default function MainNavbar() {
  return (
    <>
          <Navbar expand="lg" className="bg-body-tertiary">
          <Container>
            <Navbar.Brand href="/">MuseMatch</Navbar.Brand>
            <Navbar.Toggle aria-controls="basic-navbar-nav" />
            <Navbar.Collapse id="basic-navbar-nav">
              <Nav className="me-auto">
                <Nav.Link href="/">Home</Nav.Link>
                <Nav.Link href="match">Match</Nav.Link>
                <Nav.Link href="contacts">Contacts</Nav.Link>
                <Nav.Link href="give">Give</Nav.Link>
                <NavDropdown title="My Account" id="profile-dropdown">
                  <NavDropdown.Item href="my-profile">View profile</NavDropdown.Item>
                  <NavDropdown.Divider />
                  <NavDropdown.Item href="settings">
                    Settings
                  </NavDropdown.Item>
                </NavDropdown>
              </Nav>
            </Navbar.Collapse>
          </Container>
          </Navbar>
          </>
  );
}