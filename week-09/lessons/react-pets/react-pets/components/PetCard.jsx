// src/PetCard.jsx

export default function PetCard(props) {
    // Add props to the parameters
    const pet = props.pet; // Get the pet object out of props
    console.log(props); // What's in here?

    return (
        <div>
            <div>
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
                <a href='/'>Edit</a>
                <a href='/'>Delete</a>
            </div>
        </div>
    );
}

