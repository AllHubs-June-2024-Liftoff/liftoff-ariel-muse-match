import { useEffect, useState } from 'react'
import './App.css'
import { ThemeProvider } from './components/Themes.jsx'
import DisplayArtworks from './components/DisplayArtworks.jsx'
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom'
import Layout from './components/layout/Layout'
import Home from './components/pages/Home'
import Stats from './pages/profile/Stats.jsx'
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


function App() {


  return (
    <Router>
      <ThemeProvider>
        <AuthProvider>
          <Layout>
            <Routes>
             <Route path="/" element={<Home />} />
             <Route path="/login" element={<Login />} />
             <Route path="/sign-up" element={<SignUp />} />

             <Route element={<ProtectedRoute />}>
               <Route path="profile" element={<UserProfile />} />
               <Route path="matches" element={<Matches />} />
               <Route path="settings" element={<MySettings />} />
             </Route>

             <Route path="/match" element={<Match />} />
             <Route path="/contacts" element={<Contact />} />
             <Route path="/give" element={<Give />} />
             <Route path="/stats" element={<Stats />} />
             <Route path="/all" element={<DisplayArtworks />} />
           </Routes>

          </Layout>
        </AuthProvider>
      </ThemeProvider>
    </Router>
  )
}
export default App;

