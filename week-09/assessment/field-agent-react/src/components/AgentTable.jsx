function AgentTable({ agents }) {
    return (
        <table className="table table-striped">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>DOB</th>
                    <th>Height</th>
                    <th>&nbsp;</th>
                </tr>
            </thead>
            <tbody>
                {agents.map(agent => (
                    <tr key={agent.agentId}>
                        <td>{agent.firstName}{agent.middleName ? " " + agent.middleName : ""} {agent.lastName}</td>
                        <td>{agent.dob}</td>
                        <td>{agent.heightInInches}</td>
                        {/* TODO: Replace buttons with React Router Links (and style appropriately).
                        The delete link should navigate to a confirm delete component.
                        The edit link should navigate to the AgentForm and pre-populate the agent. */}
                        <td>
                            <button type="button" className="btn btn-danger me-2" onClick={() => alert('implement delete!')}>Delete</button>
                            <button type="button" className="btn btn-info" onClick={() => alert('implement update!')}>Edit</button>
                        </td>
                    </tr>
                ))}
            </tbody>
        </table >
    );
}

export default AgentTable;