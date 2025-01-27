import React, {createContext, useContext, useState, useEffect} from "react";
import {useNavigate} from "react-router";


const AuthContext = createContext();

export const  AuthProvider = ({children}) => {
  const [isAuthenticated, setIsAuthenticated] = useState(true);
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();
  const [csrfToken, setCsrfToken] = useState();

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

  // const login = () => {
  //   sessionStorage.setItem("token", "test-token");
  //   setIsAuthenticated(true)
  //   navigate("/my-profile")
  // };

  const getCsrfToken = async () => {
    try {
      const response = await fetch("http://localhost:8080/csrf", {
          credentials: "include",
      });
      const csrfToken = await response.json();
      setCsrfToken(csrfToken);
      return csrfToken.token;
    } catch (err) {
      console.error("Error fetching CSRF token:", err);
      return null;
    }
  };


  const login = async (username, password) =>{
    const csrfToken = await getCsrfToken();
    try {
      const response = await fetch("http://localhost:8080/login", {
          method: "POST",
          headers: {
              "Content-Type": "application/x-www-form-urlencoded",
              "X-XSRF-TOKEN": csrfToken,
          },
          body: new URLSearchParams({
              username,
              password,
          }),
          credentials: "include",
      });
      console.log(csrfToken)

      if (response.ok) {
        setIsAuthenticated(true)
        navigate("/");
      } else {
          setError("Invalid username or password");
      }
    } catch (err) {
        setError("Something went wrong. Please try again.");
    }
  }
  

  return (
    <AuthContext.Provider value={{ isAuthenticated, login, loading, getCsrfToken}}>
      {!loading && children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => useContext(AuthContext);