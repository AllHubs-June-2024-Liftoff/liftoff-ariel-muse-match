import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "./AuthContext";
import { Outlet } from "react-router-dom";

export default function ProtectedRoute() {
  const navigate = useNavigate();
  const { isAuthenticated } = useAuth();

  useEffect(() => {
    if (!isAuthenticated) {
      navigate("/login", { replace: true });
    }
  }, [isAuthenticated, navigate]);

  // If not authenticated, the user is already being redirected.
  // Returning null avoids rendering unnecessary components
  if (!isAuthenticated) {
    return null;
  }

  // Render nested routes using Outlet when authenticated
  return <Outlet />;
}
