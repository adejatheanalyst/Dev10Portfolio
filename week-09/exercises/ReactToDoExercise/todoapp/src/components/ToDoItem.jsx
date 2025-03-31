import React from "react";

export default function ToDoItem({id, task, done, handleDone}) {
    // const  {id, task, done} = props;
    console.log(done)
    return <div>
        <ul>
            <li key={id}
                className={"listItem"}
                style={{textDecoration: done ? 'line-through' : 'none'}}
            >
                {task} - {done ? 'Done' : 'Not Done'}
            </li>
            {!done && <button onClick={() => handleDone(id)}>Mark Done</button>}
        </ul>
    </div>
}