import React, { useEffect } from 'react'
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom'
import { useState } from 'react'
import Layout from './components/layout/Layout'
import Home from './components/pages/Home'
import './styles/App.css'
import UserProfile from './components/pages/UserProfile'
import MySettings from './components/pages/MySettings'
import SignUp from './components/pages/Signup'
import Login from './components/pages/Login'
import ProtectedRoute from './components/auth/ProtectedRoute'
import { AuthProvider, useAuth } from './components/auth/AuthContext'
import Matches from './components/pages/Matches'
import Match from './components/pages/Match'
import Contact from './components/pages/Contact'
import Give from './components/pages/Give'
import DisplayArtworks from './components/DisplayArtworks'

function App() {

  return (
    <Router>
      <AuthProvider>
        <Layout>
          <Routes>
            <Route path='/' element={<Home/>}/>
            <Route path="/profile" element={<UserProfile/>}/>
            <Route path="/profile/matches" element={<Matches/>}/>
            {/* <Route path='my-profile' element={<UserProfile/>}/> */}
{/*             <Route path='settings' */}
{/*             element={ */}
{/*               <ProtectedRoute> */}
{/*                 <MySettings/> */}
{/*               </ProtectedRoute>} */}
{/*             /> */}
            {/* <Route path='settings' element={<MySettings/>}/> */}
            <Route path='sign-up' element={<SignUp/>}/>
            <Route path='login' element={<Login/>} />
            <Route path='match' element={<Match/>}/>
            <Route path="all" element={<DisplayArtworks/>}/>
            <Route path='contacts' element={<Contact/>} />
            <Route path='give' element={<Give/>} />
          </Routes>
        </Layout>
      </AuthProvider>
    </Router>
  )
}

export default App;

