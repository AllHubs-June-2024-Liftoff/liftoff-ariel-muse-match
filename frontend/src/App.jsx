import { useEffect, useState } from "react";
import "./App.css";
import LoadArtworks from "./components/DisplayArtworks.jsx";
import Stats from "./pages/profile/Stats.jsx";

function App() {
  return (
    <>
      <div>
        <h1>MuseMatch: Now completely Bug free</h1>
        <div>
          <LoadArtworks />
        </div>
        <div>
          <Stats />
        </div>
      </div>
    </>
  );
}

export default App;
