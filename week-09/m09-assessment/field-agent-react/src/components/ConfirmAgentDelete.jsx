import {Link, useNavigate, useParams} from "react-router-dom";
import {useEffect, useState} from "react";

export default function ConfirmAgentDelete(){
      // The delete route should contain an agent id. 
  // Use that id to fetch a single agent, display their name, 
  // and then either delete or cancel. 
  // If the agent isn't found. Redirect to the AgentList route.



    const params = useParams()
    const navigate = useNavigate()
    const [agents, setAgent] = useState({})

    useEffect(() => {
            fetch(`http://localhost:8080/api/agent/${params.agentId}`)
                .then(response => {
                    if (response.status >= 200 && response.status < 300) {
                        response.json().then(agent => setAgent(agent))
                    } else {
                        alert("Agent not Found")
                        navigate("/agents")
                    }
                })
    }, [])

    return(
        <div>
           <p>Are you sure you want to delete agent {params.agentId}?</p>
            <p>First Name: {agents.firstName}</p>
            <p>Middle Name: {agents.middleName}</p>
            <p>Last Name: {agents.lastName}</p>
            <p>Date of Birth: {agents.dob}</p>
            <p>Height In Inches: {agents.heightInInches}</p>
            <button onClick={() =>
            fetch(`http://localhost:8080/api/agent/${params.agentId}`, {method: "DELETE"})
                .then(_ => navigate("/agents"))
            } type="button" className="btn btn-danger me-2" >Delete</button>
            <Link to={"/agents"} type="button" className="btn btn-warning">Cancel</Link>
        </div>
    )
}