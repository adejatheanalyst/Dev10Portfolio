// src/PetCard.jsx

import {Link} from "react-router-dom";

export default function PetCard(props) {
	// Add props to the parameters
	// eslint-disable-next-line react/prop-types
	const pet = props.pet; // Get the pet object out of props
	console.log(props); // What's in here?

	return (
		<div>
			<div>
				{/* eslint-disable-next-line react/prop-types */}
				<img src={pet.imageUrl} alt={`${pet.name}, a ${pet.breed}`} />
			</div>
			<div>
				<h2>Name: {pet.name}</h2>
				<h3>Breed: {pet.breed}</h3> <ul>
				<li>DOB: {pet.dob ?? 'Unknown'}</li>
				<li>Vaccinations: {pet.vaccinationStatus}</li>
				<li>Adopted: {pet.adopted ? 'Yes' : 'Not yet'}</li>
			</ul>
			</div>
			<div>
				<Link to={`/pets/edit/${pet.petId}`} className='btn btn-warning me-2'>Edit</Link>
				<Link to={`/pets/delete/${pet.petId}`} className='btn btn-danger'>Delete</Link>
			</div>
		</div>
	);
}
