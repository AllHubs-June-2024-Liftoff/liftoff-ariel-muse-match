import { useEffect } from "react";
import {useNavigate} from "react-router";
import { useAuth } from "./AuthContext";

export default function ProtectedRoute ({children}) {
  const navigate = useNavigate();
  const {isAuthenticated} = useAuth();
  
  useEffect(() =>{
    if (!isAuthenticated) {
      navigate("/login");
    }
  }, [isAuthenticated, navigate]);

  if (!isAuthenticated) {
    return null;
  }
  return children;
}