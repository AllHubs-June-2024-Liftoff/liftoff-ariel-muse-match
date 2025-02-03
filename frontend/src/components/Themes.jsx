import React, { createContext, useState, useEffect, useContext } from "react";
import { useAuth } from "./auth/AuthContext.jsx";


export const ThemeContext = createContext({ 
    isLight: true, 
    toggleTheme: () => {},
 });

export const ThemeProvider = ({ children }) => { //theme context is the parent
    const { isAuthenticated, getCsrfToken } = useAuth();
    const [isLight, setIsLight] = useState(true);

    // Fetch initial theme preference
    useEffect(() => {
        if (!isAuthenticated) {
            return;
        }
        
        const fetchThemePreference = async () => {
            try {
                //Check for session validity
                const sessionResponse = await fetch('http://localhost:8080/validate-session', {
                    method: 'GET',
                    credentials: 'include',
                });

                if (!sessionResponse.ok) {
                    //Session invalid, prompt a login again
                    console.log("User not authenticated");
                    return;
                }

                //Get csrf token
                const csrfToken = await getCsrfToken();

                //Fetch theme preference
                const response = await fetch('http://localhost:8080/api/user/preferences', {
                    method: 'GET',
                    credentials: 'include',
                    headers: {
                        'Content-Type': 'application/json',
                        'Accept': 'application/json',
                        'X-XSRF-TOKEN': csrfToken,
                    }
                });
    
                if (response.status === 401) {
                    console.log("user not authenticated");
                    return;
                }
    
                if (!response.ok) {
                    throw new Error(`HTTP error. Status: ${response.status}`);
                }
    
                const data = await response.json();
                setIsLight(data.isLight);
            } catch (error) {
                console.error('Error fetching theme:', error);
            }
        };
    
        fetchThemePreference();
    }, [isAuthenticated]);


    useEffect(() => {
    //If the user isn't logged in, it will just render children w/o theme logic
    if (!isAuthenticated) {
        return;
    }

    //set theme based on the user preference
    document.body.setAttribute('isLight', isLight ? 'true' : 'false');
}, [isLight, isAuthenticated]);

    const toggleTheme = async () => {
        try {
       const csrfToken = await getCsrfToken();
       const newTheme = !isLight;
       const response = await fetch('http://localhost:8080/api/theme/change', {
        method: 'PUT',
        credentials: 'include',
        headers: {
            'Content-Type': 'application/json',
            'X-XSRF-TOKEN': csrfToken,
        },
        body: JSON.stringify({ theme: newTheme }),
       });

       if (!response.ok) {
              console.error('Failed to save theme preference');
       }

        setIsLight(prev => !prev); //Toggle based on previous theme preference state
    } catch (error) {
        console.error('Error saving theme:', error);
    }
    };

    if (!isAuthenticated) {
        return <>{children}</>
    }

    return (
        <ThemeContext.Provider value={{ isLight, toggleTheme }}>
            {children}
        </ThemeContext.Provider>
    );
};


export const useIsLight = () => {
    const context = useContext(ThemeContext);
    if (!context) {
        throw new Error("useIsLight must be used within a ThemeProvider");
    }
    return context;
};