import { useEffect, useState } from 'react';
import NavBar from './components/NavBar';
import SolarPanelsForm from './components/SolarPanelForm';
import SolarPanelsList from './components/SolarPanelsList';
import DeleteSolarPanel from './components/DeleteSolarPanel';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import './App.css'
import UserForm from './components/UserForm';
import LoginForm from './components/LoginForm';
import MyPanels from './components/MyPanels';
import { Navigate } from 'react-router-dom';



function App() {
	const [loggedInUser, setLoggedInUser] = useState(null);
	const [hasCheckedForUser, setHasCheckedForUser] = useState(false);

	useEffect(() => {
		const loggedInUser = localStorage.getItem("loggedInUser");
		if (loggedInUser) {
			setLoggedInUser(JSON.parse(loggedInUser));
		}
		setHasCheckedForUser(true);
	}
	, []);

	if(!hasCheckedForUser){
		return <div>Loading...</div>;
	}

	return (
		<Router>
			<div className='container'>
				<NavBar loggedInUser={loggedInUser} setLoggedInUser={setLoggedInUser}/>
				<main>
				<h1 className='mb-3'>Solar Panels</h1>
				{ loggedInUser === null ? null : <h2>Hello, {loggedInUser.username}!</h2> }
					<Routes>
						<Route path="/" element={<div>Welcome to the farm</div>} />
						<Route path="/list" element={<SolarPanelsList loggedInUser={loggedInUser} />} />
						<Route path="/add" element={loggedInUser === null ? <Navigate to="/login" /> :
							<SolarPanelsForm loggedInUser={loggedInUser} />} />
						<Route path="/edit/:solarPanelId" element={loggedInUser === null ? <Navigate to="/login" /> :
							<SolarPanelsForm loggedInUser={loggedInUser} setLoggedInUser={setLoggedInUser} />} />
						<Route path="/delete/:solarPanelId" element={ loggedInUser === null ? <Navigate to="/login" /> : 
							<DeleteSolarPanel loggedInUser={loggedInUser} setLoggedInUser={setLoggedInUser} />} />
						<Route path="/myPanels" element={loggedInUser === null ? <Navigate to="/login" /> :
							<MyPanels loggedInUser={loggedInUser} setLoggedInUser={setLoggedInUser} />} />
						<Route path="/signup" element={ loggedInUser !== null ? <Navigate to="/list" /> :
							<UserForm setLoggedInUser={setLoggedInUser} />} />
						<Route path="/login" element={ loggedInUser !== null ? <Navigate to="/list" /> :
							<LoginForm setLoggedInUser={setLoggedInUser} />} />
						<Route path="*" element={<div>I don't know how you got here, but click a nav link to go somewhere else</div>} />
					</Routes>
				</main>
			</div>
		</Router>
	);
}

export default App
