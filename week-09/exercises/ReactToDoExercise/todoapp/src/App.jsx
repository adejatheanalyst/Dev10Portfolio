import { useState } from 'react'
import './App.css'
import AddTodo from "./components/AddTodo.jsx";
import ToDoList from "./components/ToDoList.jsx";

export default function App() {
    const [list , setList] = useState([
        { id: 1, task: 'Walk the dog', done: true },
        { id: 2, task: 'Laundry', done: false },
        { id: 3, task: 'Dishes', done: false },
    ]);
    // const [showForm, setShowForm] = useState(false);
    // const [done, setDone] = useState(false)
    function handleDone(id) {
        // .map() returns a new array of transformed elements
        const updatedList = list.map(toDo => {
            // If we've encountered the toDo with a matching id,
            // copy the object and update its done property to true
            // and return the new toDo to accumulate it in the resulting array
            if (list.id === id) {
                return {
                    ...toDo,
                    done: true,
                };
            }
            // This isn't the toDo that we need to update
            // so just return it to accumulate it in the resulting array
            return toDo;
        });
        setList(updatedList);
    }

        return (
            <div className="App">
                <header className="App-header">
                    <h1>TodoApp</h1>
                </header>
                <AddTodo/>
                <ToDoList list={list} handleDone={handleDone}/>)
            </div>
        )
    }

