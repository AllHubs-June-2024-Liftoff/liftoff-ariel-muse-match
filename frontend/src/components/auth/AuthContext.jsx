import React, {createContext, useContext, useState, useEffect} from "react";
import {useNavigate} from "react-router";


const AuthContext = createContext();

export const  AuthProvider = ({children}) => {
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();

  useEffect(() => {
    const token = sessionStorage.getItem("token");
    if (token) {
      setIsAuthenticated(true);
      setLoading(false);
      console.log(token);
    } else {
      setLoading(false);
    }
  }, []);

  const login = () => {
    sessionStorage.setItem("token", "test-token");
    setIsAuthenticated(true)
    navigate("/my-profile")
  };

  return (
    <AuthContext.Provider value={{ isAuthenticated, login, loading}}>
      {!loading && children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => useContext(AuthContext);