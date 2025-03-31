import { useState } from 'react';
import Header from "../components/Header.jsx";
import PetCards from "../components/PetCards.jsx";
import PetForm from "../components/PetForm.jsx";

function App() {
    const [showForm, setShowForm] = useState(false);
 return (
        <div>
            <Header />
            {showForm === true ? (
                <PetForm setShowForm={setShowForm} />
            ) : (
                <PetCards setShowForm={setShowForm} />
            )}
        </div>
    )
}

export default App;

