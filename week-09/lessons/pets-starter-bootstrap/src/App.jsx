import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import About from './Components/About/About.jsx';
import Header from './Components/Header.jsx';
import PetCards from './Components/PetCard/PetCards.jsx';
import NotFound from "./Components/NotFound.jsx";
import {useState} from "react";
import PetForm from "./Components/PetForm.jsx";
import Home from "./Components/Home.jsx";
import PetDeleteConfirm from "./Components/PetDeleteConfirmation.jsx";
import Error from "./Components/Error.jsx";
import Contact from './Components/Contact/Contact.jsx';

const initialPets = [{
	petId: 1,
	name: 'Wishbone',
	type: 'DOG',
	breed: 'Jack Russell Terrier',
	dob: '2015-05-05',
	adopted: true,
	vaccinationStatus: 'UNKNOWN',
	imageUrl: 'https://i.imgur.com/yGzjvPj.jpg',
},
	{
		petId: 2,
		name: 'Whiskers',
		type: 'CAT',
		breed: 'Tabby',
		dob: '2020-01-01',
		adopted: false,
		vaccinationStatus: 'UP_TO_DATE',
		imageUrl: 'https://i.imgur.com/vlnDvGW.jpg',
	},
	{
		petId: 3,
		name: 'Archie',
		type: 'DOG',
		breed: 'Golden Retriever',
		dob: '2022-12-15',
		adopted: false,
		vaccinationStatus: 'NOT_UP_TO_DATE',
		imageUrl: 'https://i.imgur.com/IeR2bMU.jpg',
	}];
function App() {
	// const [showForm, setShowForm] = useState(false);
	// The `Window` object's `location` property gives us access to a `Location` object that provides information
	// about the current location of the document. The `Location` object's `pathname` property is a string
	// representing the path of the URL for the current location.
	// Learn  more: https://developer.mozilla.org/en-US/docs/Web/API/Location/pathname
// 	const path = window.location.pathname;
// 	switch (path) {
// 		case '/about':
// 			return (
// 				<div className='container'>
// 					<Header />
// 					<About />
// 				</div>
// 			);
// 		case '/pets':
// 			return (
// 				<div className='container'>
// 					<Header />
// 					<PetCards />
// 				</div>
// 			);
// 		case '/pets/add':
// 			return (
// 				<div className='container'>
// 					<Header />
// 					<PetForm />
// 				</div>
// 			);
// 		default:
// 			return (
// 				<div className='container'>
// 					<Header />
// 					<About />
// 				</div>
// 			);
// 	}
// }
	const [pets, setPets] = useState(initialPets);
	function handleAddPet(newPet) {
		// 2. Spread original pets, add in new pet from form
		setPets([...pets, newPet]);
		//whats happening here
		// const petsCopy = [...pets];
		// petsCopy.push(newPet);
		// setPets(petsCopy);
	}
	return (
		<Router>
			{/* 1. Wrap all components in <Router> */}
			<Header /> {/* 2. Components that always live on the page and use React Router elements */}
			<Routes>
				{/* 3. <Routes> contains all the individual Route components*/}
				<Route path='/about' element={<About />} /> {/* 4. A <Route> associates a path with a component */}
				<Route path='/pets' element={<PetCards pets={pets}/>} />
				<Route path='/pets/add' element={<PetForm handleAddPet={handleAddPet} />} />
				<Route path='/pets/edit/:petId' element={<PetForm />} />
				<Route path='/pets/delete/:petId' element={<PetDeleteConfirm />} />
				<Route path='*' element={<NotFound />} />
				<Route path={'/'} element={<Home />} />
				<Route path='/error' element={<Error />} />
				<Route path='/contact' element={<Contact />} />
			</Routes>
		</Router>
	);
}

export default App
