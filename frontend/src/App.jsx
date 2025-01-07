import React from 'react'
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom'
import { Navbar, Nav, Container } from 'react-bootstrap'
import { useState } from 'react'
import Layout from './components/layout/Layout'
import Home from './components/pages/Home'
import './styles/App.css'
import UserProfile from './components/pages/UserProfile'
import MySettings from './components/pages/MySettings'
import SignUp from './components/pages/Signup'
import Login from './components/pages/Login'


function App() {
  return (
    <Router>
      <Layout>
        <Routes>
          <Route path='/' element={<Home/>}/>
          <Route path='my-profile' element={<UserProfile/>}/>
          <Route path='settings' element={<MySettings/>}/>
          <Route path='sign-up' element={<SignUp/>}/>
          <Route path='login' element={<Login/>} />
        </Routes>
      </Layout>
    </Router>
  )
}

export default App
