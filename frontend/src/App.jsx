import { ThemeProvider } from "./components/Themes.jsrks.jsx";
import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Layout from "./components/layout/Layout";
import Home from "./components/pages/Home";
import "./styles/App.css";
import UserProfile from "./components/pages/UserProfile";
import MySettings from "./components/pages/MySettings";
import SignUp from "./components/pages/Signup";
import Login from "./components/pages/Login";
import ProtectedRoute from "./components/auth/ProtectedRoute";
import { AuthProvider } from "./components/auth/AuthContext";
import Match from "./components/pages/Match";
import Contact from "./components/pages/Contact";
import Give from "./components/pages/Give";
import Stats from "./components/pages/Stats";
import Matches from "./components/pages/Matches";

function App() {
	return (
		<Router>
			<ThemeProvider>
				<AuthProvider>
					<Layout>
						<Routes>
							<Route path="/" element={<Home />} />
							<Route
								path="my-profile"
								element={
									<ProtectedRoute>
										<UserProfile />
									</ProtectedRoute>
								}
							/>
							{/* <Route path='my-profile' element={<UserProfile/>}/> */}
							<Route
								path="settings"
								element={
									<ProtectedRoute>
										<MySettings />
									</ProtectedRoute>
								}
							/>
							{/* <Route path='settings' element={<MySettings/>}/> */}
							<Route
								path="stats"
								element={
									<ProtectedRoute>
										<Stats />
									</ProtectedRoute>
								}
							/>
							<Route
								path="matches"
								element={
									<ProtectedRoute>
										<Matches />
									</ProtectedRoute>
								}
							/>
							{/* <Route path='stats' element={<Stats/>}/> */}
							<Route path="sign-up" element={<SignUp />} />
							<Route path="login" element={<Login />} />
							<Route path="match" element={<Match />} />
							<Route path="contacts" element={<Contact />} />
							<Route path="give" element={<Give />} />
						</Routes>
					</Layout>
				</AuthProvider>
			</ThemeProvider>
		</Router>
	);
}

export default App;
