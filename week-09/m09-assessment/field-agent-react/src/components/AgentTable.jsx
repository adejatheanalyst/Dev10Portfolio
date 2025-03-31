import {Link} from "react-router-dom";

function AgentTable({ agents }) {

    return (
        <table className="table table-striped">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>DOB</th>
                    <th>Height</th>
                    <th>Aliases</th>
                    <th>&nbsp;</th>
                </tr>
            </thead>
            <tbody>
                {agents.map(agent => (
                    <tr key={agent.agentId}>
                        <td>{agent.firstName}{agent.middleName ? " " + agent.middleName : ""} {agent.lastName}</td>
                        <td>{agent.dob}</td>
                        <td>{agent.heightInInches}</td>
                        <td><Link to ={`/agents/alias/${agent.agentId}`}>View Aliases</Link></td>
                        {/* TODO: Replace buttons with React Router Links (and style appropriately).
                        The delete link should navigate to a confirm delete component.
                        The edit link should navigate to the AgentForm and pre-populate the agent. */}
                        <td>
                            <Link type="button" className="btn btn-danger me-2 "  to ={`/agents/delete/${agent.agentId}`}>Delete</Link>
                            <Link type="button" className="btn btn-info" to = {`/agents/update/${agent.agentId}`}> Edit</Link>
                        </td>
                    </tr>
                ))}
            </tbody>
        </table >
    );
}

export default AgentTable;