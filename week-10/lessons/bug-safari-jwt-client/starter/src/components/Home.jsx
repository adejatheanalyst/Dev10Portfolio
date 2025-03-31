import { useEffect, useState } from "react";
import Sighting from "./Sighting";

function Home() {
  const [sightings, setSightings] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/sighting")
      .then((response) => response.json())
      .then((data) => setSightings(data));
  }, []); // this will happen only once when the component is loaded

  const handleDelete = (sightingId) => {
    console.log("Need to delete", sightingId);
  };

  return sightings.map((sighting) => (
    <Sighting key={sighting.sightingId} sighting={sighting} 
      handleDelete={handleDelete} />
  ));
}

export default Home;
