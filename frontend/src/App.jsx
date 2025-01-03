import { useEffect, useState } from 'react'
import './App.css'
import LoadArtworks from './components/DisplayArtworks.jsx'

function App() {


  return (
    <>
      <div>
        <h1>MuseMatch: Now completely Bug free</h1>
        <LoadArtworks />
      </div>
    </>
  );
}

export default App;

