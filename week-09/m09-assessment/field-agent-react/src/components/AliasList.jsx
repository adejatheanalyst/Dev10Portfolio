import {useEffect, useState} from "react";
import {useParams} from "react-router-dom";
import AliasTable from "./AliasTable.jsx";

export default function AliasList(){
    const [alias, setAlias] = useState([])
    const params= useParams()
    console.log(params)
    const [agent] = useState({})

    useEffect(() => {
        const fetchAlias = async () =>{
            const response = await fetch(`http://localhost:8080/api/alias`)
            console.log(response)
            if(response.ok){
                setAlias(await response.json())
                console.log(response)
                console.log(alias)
            }else {
                // setAgent([]);
                setAlias([])
            }
        };
        fetchAlias()
    }, []);

    return <div>
        {agent.aliases === 0 ?
        <div className="alert alert-warning py-4">
        No Aliases found for that Agent.
            Would you like to Add an Alias?
            <button>Add Alias</button>
        </div>
            :<AliasTable alias={alias}/>
        }
    </div>
}