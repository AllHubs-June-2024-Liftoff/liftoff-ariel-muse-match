import React from 'react';
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import NavDropdown from 'react-bootstrap/NavDropdown'
import MainNavbar from '../common/MainNavbar';
import { Row } from 'react-bootstrap';

export default function Layout({ children }) {
  return(

    <>
      <MainNavbar/>
      <main>
        <Container id='main-container'>
          {children}
        </Container>
      </main>
      </>


  );
}
