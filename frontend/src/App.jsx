import { ThemeProvider } from "./components/Themes.jsx";
import DisplayArtworks from "./components/DisplayArtworks.jsx";
import React, { useEffect, useState } from "react";
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import Layout from "./components/layout/Layout";
import Home from "./components/pages/Home";
import "./styles/App.css";
import UserProfile from "./components/pages/UserProfile";
import MySettings from "./components/pages/MySettings";
import SignUp from "./components/pages/Signup";
import Login from "./components/pages/Login";
import ProtectedRoute from "./components/auth/ProtectedRoute";
import { AuthProvider, useAuth } from "./components/auth/AuthContext";
import Match from "./components/pages/Match";
import Contact from "./components/pages/Contact";
import Give from "./components/pages/Give";
import Stats from "./components/pages/Stats";
import Matches from "./components/pages/Matches";
import ViewPublicProfile from "./components/pages/ViewPublicProfile.jsx";
import NotFoundPage from "./components/pages/NotFoundPage.jsx"

function App() {
	return (
		<Router>
				<AuthProvider>
				<ThemeProvider>
					<Layout>
						<Routes>
							<Route path="/" element={<Home />} />
							<Route
								path="/my-profile"
								element={
									<ProtectedRoute>
										<UserProfile />
									</ProtectedRoute>
								}
							/>
							{/* <Route path='my-profile' element={<UserProfile/>}/> */}
							<Route
								path="/settings"
								element={
									<ProtectedRoute>
										<MySettings />
									</ProtectedRoute>
								}
							/>
							{/* <Route path='settings' element={<MySettings/>}/> */}
							<Route
								path="/stats"
								element={
									<ProtectedRoute>
										<Stats />
									</ProtectedRoute>
								}
							/>
							"
							<Route path="/sign-up" element={<SignUp />} />
							<Route path="/login" element={<Login />} />
							<Route 
                path="/matches"
                element={
                  <ProtectedRoute>
                    <Matches />
                  </ProtectedRoute>
                  } 
                />
							<Route path="/contacts" element={<Contact />} />
							<Route path="/give" element={<Give />} />
              <Route path="/profile/:username" element={<ViewPublicProfile/>}/>
              <Route path="*" element={<NotFoundPage />} />
						</Routes>
					</Layout>
					</ThemeProvider>
				</AuthProvider>
		</Router>
	);
}

export default App;
