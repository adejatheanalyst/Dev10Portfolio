import { useState } from 'react'

import AgentForm from "./components/AgentForm";
import AgentList from "./components/AgentList";
import Landing from "./components/Landing";
import Nav from "./components/Nav";
import {BrowserRouter as Router, Routes, Route} from "react-router-dom";
import ConfirmAgentDelete from "./components/ConfirmAgentDelete.jsx";
import './App.css'
import AliasList from "./components/AliasList.jsx";

// const componentMap = {
//   "form": AgentForm,
//   "list": AgentList,
//   "landing": Landing
// }

function App() {
    // const [view, setView] = useState("landing");
    // const Component = componentMap[view];
  // TODO: Implement React Router V6.
    return(
        <Router>
            <div className='container'>
            <Nav />
            {/*<Component setView={setView} />*/}
                <main>
            <Routes>
                <Route path ="/" element={<Landing />} />
                <Route path ="/agents" element={<AgentList />} />
                <Route path ="/agents/add" element={<AgentForm />} />
                {/*// TODO: Add routes for update and delete.*/}
                <Route path ="/agents/update/:agentId" element={<AgentForm />} />
                {/*// TODO: Add a ConfirmAgentDelete component that renders with the delete route.*/}
                <Route path ="/agents/delete/:agentId" element={<ConfirmAgentDelete />} />
                <Route path ="/agents/alias/:agentId" element={<AliasList />} />
                <Route path ="*" element={<div>Page not Found</div>}/>
            </Routes>
                </main>
            </div>
        </Router>
    )

 
  // The delete route should contain an agent id. 
  // Use that id to fetch a single agent, display their name, 
  // and then either delete or cancel. 
  // If the agent isn't found. Redirect to the AgentList route.

}

export default App
