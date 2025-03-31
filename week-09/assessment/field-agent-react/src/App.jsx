import { useState } from 'react'

import AgentForm from "./components/AgentForm";
import AgentList from "./components/AgentList";
import Landing from "./components/Landing";
import Nav from "./components/Nav";

const componentMap = {
  "form": AgentForm,
  "list": AgentList,
  "landing": Landing
}

function App() {

  // TODO: Implement React Router V6.
  const [view, setView] = useState("landing");
  const Component = componentMap[view];

  // TODO: Add routes for update and delete.

  // TODO: Add a ConfirmAgentDelete component that renders with the delete route.
  // The delete route should contain an agent id. 
  // Use that id to fetch a single agent, display their name, 
  // and then either delete or cancel. 
  // If the agent isn't found. Redirect to the AgentList route.

  return (
    <main className="container">
      <Nav view={view} setView={setView} />
      <Component setView={setView} />
    </main>
  );
}

export default App
