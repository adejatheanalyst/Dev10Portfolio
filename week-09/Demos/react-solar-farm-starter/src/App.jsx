
import NavBar from './components/NavBar';
import SolarPanelsForm from './components/SolarPanelForm';
import SolarPanelsList from './components/SolarPanelsList';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import './App.css'
import DeleteConfirm from './components/DeleteConfirm';


function App() {

	return (
		<Router>
			<div className='container'>
				<NavBar />
				<main>
					<h1 className='mb-3'>Solar Panels</h1>
					<Routes>
						<Route path="/" element={<div>Welcome to the farm</div>} />
						<Route path="/list" element={<SolarPanelsList  />} />
						<Route path="/add" element={<SolarPanelsForm  />} />
						<Route path="/edit/:id" element={<SolarPanelsForm  />} />
						<Route path="/delete/:id" element={<DeleteConfirm />} />
						<Route path="*" element={<div>I don't know how you got here, but click a nav link to go somewhere else</div>} />
					</Routes>
				</main>
			</div>
		</Router>
	);
}

export default App
