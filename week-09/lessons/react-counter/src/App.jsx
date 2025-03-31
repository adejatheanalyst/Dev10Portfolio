import { useState } from 'react'
import './App.css'

function App() {
    // let count = 0;
    const [count, setCount] = useState(0)

    function handleIncrement() {
        setCount(count + 1)
        console.log(count)
    }
    function handleDecrement() {
        setCount(count - 1)
        console.log(count)
    }

  return (
      <div className="App">
          <h1>{count}</h1>
          <div>
        <button onClick={handleIncrement}>+</button>
        <button onClick={handleDecrement} >-</button>
        </div>
      </div>
        )}


export default App
