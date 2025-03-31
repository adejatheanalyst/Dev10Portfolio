import ToDoItem from "./ToDoItem.jsx";
import React from "react";

export default function ToDoList({list, handleDone}) {
    return <div>
        <h1>To Do: List</h1>
        <ul>
            {list.map((item) => (
                <ToDoItem key={item.id} task={item.task} done={item.done} handleDone = {handleDone}/>
            ))}
        </ul>
    </div>
}