import { useState, useEffect } from "react";
import {Link, useNavigate, useParams} from "react-router-dom";

// TODO: Modify this component to support update/edit.
// An update URL should have an agent id.
// Use that id to fetch a single agent and populate it in the form.
const initial_agent ={
    firstName: "",
    middleName: "",
    lastName: "",
    dob: "",
    heightInInches: ""
}
function AgentForm() {
    const navigate = useNavigate();
    const params = useParams()// created params to get agent Id
    console.log(params)
    const [agent, setAgent] = useState({});
    const [errors, setErrors] = useState([]);

    useEffect(() => { // created a use effect to populate form field with agent information when updating.
        if (params.agentId === undefined) {// if agent id is undefined then it means there is not agent o edit . keep field clear
            setAgent(initial_agent)
        } else {
            fetch(`http://localhost:8080/api/agent/${params.agentId}`) // fetch agent from data base by agent id
                .then(response => {
                    if (response.status >= 200 && response.status < 300) { // if response from fetch call is ok then set agent informtation with agent pulled from data base
                        response.json().then(agent => setAgent(agent))
                    } else {
                        alert("Agent not Found")
                        navigate("/agents") // navigate back to agents if not found.
                    }
                })
        }
    }, [params.agentId]) // the use effect only goes into effect if there is an agent id


    function handleChange(evt) {

        setAgent(previous => {
            const next = { ...previous };
            next[evt.target.name] = evt.target.value;
            return next;
        });
    }
    // TODO: Modify this function to support update as well as add/create.
    function handleSubmit(evt) {
        evt.preventDefault();
        let method = "POST" // new variable for create
        let url = "http://localhost:8080/api/agent" // base url
        if(params.agentId !== undefined){ // if agent id is NOT undefined 
            method = "PUT" // use update method
            url += `/${params.agentId}` // adding agent id to the end of base url
        }
        fetch(url, { // fetch agents
            method,
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(agent)
        })
            .then(response => {
                if (response.ok) {
                    navigate("/agents") // if successfull navigate back to agents
                } else {
                    return response.json();
                }
            })
            .then(errs => {
                if (errs) {
                    return Promise.reject(errs);
                }
            })
            .catch(errs => {
                if (errs.length) {
                    setErrors(errs);
                } else {
                    setErrors([errs]);
                }
            });
    }
    // function handleCancel() { // since cancel is now a link. no need to have a cancel method.
    //     navigate("/agents")
    // }

    return (
        <>
            <h1 className="display-6">{params.agentId > 0 ? "Edit Agent": "Add an Agent"}</h1> 
            {errors && errors.length > 0 && <div className="alert alert-danger">
                <ul className="mb-0">
                    {errors.map(err => <li key={err}>{err}</li>)}
                </ul>
            </div>}
            <form onSubmit={handleSubmit}>
                <div className="row mb-3">
                    <div className="col">
                        <label className="form-label" htmlFor="firstName">First Name</label>
                        <input id="firstName" name="firstName" type="text" className="form-control" required
                            onChange={handleChange} value={agent.firstName} />
                    </div>
                    <div className="col">
                        <label className="form-label" htmlFor="middleName">Middle Name</label>
                        <input id="middleName" name="middleName" type="text" className="form-control"
                            onChange={handleChange} value={agent.middleName} />
                    </div>
                </div>
                <div className="mb-3">
                    <label className="form-label" htmlFor="lastName">Last Name</label>
                    <input id="lastName" name="lastName" type="text" className="form-control" required
                        onChange={handleChange} value={agent.lastName} />
                </div>
                <div className="row mb-3">
                    <div className="col">
                        <label className="form-label" htmlFor="dob">DOB</label>
                        <input id="dob" name="dob" type="date" className="form-control" required
                            onChange={handleChange} value={agent.dob} />
                    </div>
                    <div className="col">
                        <label className="form-label" htmlFor="heightInInches">Height (inches)</label>
                        <input id="heightInInches" name="heightInInches" type="number" className="form-control"
                            required min="36" max="96"
                            onChange={handleChange} value={agent.heightInInches} />
                    </div>
                </div>
                <div className="mb-3">
                    <button type="submit" className="btn btn-primary me-2">Save</button>
                    {/* TODO: Change this button to a React Router Link. */}
                    <Link type="button" className="btn btn-warning" to ='/agents'>Cancel</Link>
                </div>
            </form>
        </>
    );
}

export default AgentForm;