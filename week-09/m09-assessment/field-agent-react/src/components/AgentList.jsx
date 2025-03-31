import { useEffect, useState } from "react";

import AgentTable from "./AgentTable";

function AgentList() {

    const [agents, setAgents] = useState([]);

    useEffect(() => {
        const fetchAgents = async () => {
            const response = await fetch("http://localhost:8080/api/agent");
            if (response.ok) {
                setAgents(await response.json());
            } else {
                setAgents([]);
            }
        };

        fetchAgents();
    }, []);

    return (
        <>
            {agents.length == 0 ?
                <div className="alert alert-warning py-4">
                    No agents found.<br />
                    Do you want to add an agent?
                </div>
                : <AgentTable agents={agents} />}
        </>
    );
}

export default AgentList;