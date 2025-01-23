import { useEffect, useState } from 'react'
import './App.css'
import { ThemeProvider } from './components/Themes.jsx'
import DisplayArtworks from './components/DisplayArtworks.jsx'

function App() {


  return (
    <>
      <div>
        <ThemeProvider>
        <h1>MuseMatch</h1>
        <DisplayArtworks />
        </ThemeProvider>
      </div>
    </>
  );
}

export default App;

