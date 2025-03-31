import PetCard from './PetCard.jsx';
import { Link } from 'react-router-dom';
import {useEffect, useState} from 'react';



export default function PetCards() {
const [pets, setPets] = useState([]);

	useEffect(() => {fetch('http://localhost:8080/api/pets')
		.then(res => res.json())// Read the body stream to completion
		.then(setPets)  // Put the body in pets state (shorthand for .then(res => setPets(res)))
		.catch(console.error);
	}, []);

	return (
		<>
			<h1>Pets</h1>
			<Link className='btn btn-primary mb-3' to={'/pets/add'}>Add a Pet</Link>
			{/*<button*/}
			{/*	className='btn btn-primary mb-3'*/}
			{/*	onClick={() => setShowForm(true)}>*/}
			{/*	Add a Pet*/}
			{/*</button>*/}
			<div className='row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4'>
				{pets.map(pet => (
					<PetCard pet={pet} key={pet.petId} />
				))}
			</div>
		</>
	);
}
