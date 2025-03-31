import React from 'react';
import Heading from './components/Heading';
import Numbers from "./components/Numbers.jsx";
import Movies from "./components/Movies.jsx";

function App() {
    return <div>
    <Heading title="Hello, World!" />
    <Heading title2="Hello, World!" />
    <Heading title3="Hello, World!" />
        <Numbers min ={1} max ={10}/>
        <Movies/>
    </div>
}

export default App;

