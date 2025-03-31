import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { useState } from "react";
import SolarTableSection from "./components/SolarTableSection.jsx";
import NavBarSection from "./components/NavBar.jsx";
import SolarPanelForm from "./components/SolarPanelForm.jsx";
import About from "./components/About.jsx";
import Home from "./components/Home.jsx";
import ContactForm from "./components/ContactForm.jsx";
import NotFound from "./components/NotFound.jsx";
import SolarPanelDeleteConfirmation from "./components/SolarPanelDeleteConfirmation.jsx";

const initialSolarPanels = [
	{
		id: 17,
		section: 'The Ridge',
		row: 1,
		column: 1,
		yearInstalled: 2020,
		material: 'POLY_SI',
		tracking: true,
	},
	{
		id: 18,
		section: 'The Ridge',
		row: 1,
		column: 2,
		yearInstalled: 2021,
		material: 'CIGS',
		tracking: true,
	},
	{
		id: 19,
		section: 'Flats',
		row: 1,
		column: 1,
		yearInstalled: 2020,
		material: 'CD_TE',
		tracking: false,
	},
];

function App() {
	const [solarPanels, setSolarPanels] = useState(initialSolarPanels);
	function handleAddSolarPanel(newSolarPanel) {
		setSolarPanels([...solarPanels, newSolarPanel]);
	}

	return (
		<Router>
			<NavBarSection />
			<Routes>
				<Route path='/solar-panels' element={<SolarTableSection solarPanels={solarPanels} />} />
				<Route path='/solar-panels/add' element={<SolarPanelForm handleAddSolarPanel={handleAddSolarPanel} />} />
				<Route path='/solar-panels/edit/:id' element={<SolarPanelForm />} />
				<Route path='/solar-panels/delete/:id' element={<SolarPanelDeleteConfirmation />} />
				<Route path='/about' element={<About />} />
				<Route path ='/contact' element={<ContactForm />} />
				<Route path = '/' element={<Home />} />
				<Route path = '*' element={<NotFound />} />

			</Routes>

		</Router>
	)
}


export default App
