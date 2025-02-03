import React, {createContext, useContext, useState, useEffect} from "react";
import {useNavigate} from "react-router";


const AuthContext = createContext();

export const  AuthProvider = ({children}) => {
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();
  const [csrfToken, setCsrfToken] = useState();
  const [userName, setUserName] = useState("");
  const [email, setEmail] = useState("");
  const [bio, setBio] = useState("");
  const [isPublic, setIsPublic] = useState("");

  const [isLight, setIsLight] = useState(true);

  // useEffect(() => {
  //   const token = sessionStorage.getItem("token");
  //   if (token) {
  //     setIsAuthenticated(true);
  //     setLoading(false);
  //     console.log(token);
  //   } else {
  //     setLoading(false);
  //   }
  // }, []);


  useEffect(() => {
    const validateSession = async () => {
      try {
        const response = await fetch("http://localhost:8080/validate-session", {
          method: "GET",
          credentials: "include"
        });

        if(response.ok) {
          const data = await response.json();
          console.log(data);
          setIsAuthenticated(data.isAuthenticated);
          setUserName(data.username);
          setEmail(data.email);
          setBio(data.bio);
          setIsPublic(data.isPublic);
          setIsLight(data.isLight); //PLACING FOR TESTING
          setLoading(false);
        }
      } catch (error) {
        console.error("Session validation failed, error");
        setIsAuthenticated(false);
        setLoading(false);
      }
    };
    validateSession();
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
        if (response.url == "http://localhost:8080/login?error") {
          navigate("/login");
          console.error(response.url);
        } else{
          setIsAuthenticated(true);
          navigate("/");
        }
      } else {
          console.error("Invalid username or password");
      }
    } catch (err) {
        console.error("Something went wrong. Please try again.");
    }
  }
  const logout = async () => {
    const csrfToken = await getCsrfToken();
    try {
      const response = await fetch("http://localhost:8080/logout", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded",
            "X-XSRF-TOKEN": csrfToken,
        },
        credentials: "include",
      });
      if (response.ok) {
        console.log(response)
        setIsAuthenticated(false)
        navigate("/");
      } else {
        console.error("Can't logout!");
      }
    console.log("got here");
    } catch (error) {
      console.error(error);
    }
  };

  const registerUser = async (regUserName, regPassword, regEmail) => {
    const csrfToken = await getCsrfToken();
    try {
      const response = await fetch("http://localhost:8080/register", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "X-XSRF-TOKEN": csrfToken,
        },
        body: JSON.stringify({
          username: regUserName,
          password: regPassword,
          email: regEmail,
      }),
        credentials: "include",
      });

      if (response.ok) {
        login(regUserName, regPassword);
      } else {
        console.error("Can't register!");
      }
    } catch(error){
      console.error(error);
    }
  };


  return (
    <AuthContext.Provider value={{ isAuthenticated, login, loading, getCsrfToken, userName, email, logout, registerUser, bio, isPublic}}>
      {!loading && children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => useContext(AuthContext);