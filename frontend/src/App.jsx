import { useEffect, useState } from 'react'
import './App.css'
import LoadArtworks from './components/DisplayArtworks.jsx'
import Card from './components/Card.jsx'
import HorizontalScroll from './components/HorizontalScrollCard.jsx'
function App() {


//somehow use fetch artworks at match/all
  return (
    <div className="App">
      <HorizontalScroll>
      </HorizontalScroll>
    </div>
  );

}
//
//       return (
//         <div className="App">
//           <Card>
//             <h2>Hello there!</h2>
//             <p>Description</p>
//           </Card>
//         </div>
//       );



//   return (
//     <>
//       <div>
//         <h1>MuseMatch: Now completely Bug free</h1>
//         <LoadArtworks />
//         <Stats />
//       </div>
//     </>
//   );
// }

export default App;

