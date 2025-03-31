// src/components/PetDeleteConfirm.jsx

import { useEffect, useState } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';

export default function PetDeleteConfirm() {
    const [pet, setPet] = useState(null);
    const { petId } = useParams();
    const navigate = useNavigate();

    function handleDelete() {
        fetch(`http://localhost:8080/api/pets/${pet.petId}`, {
            method: 'DELETE',
        })
            .then(res => {
                if (res.ok) {
                    navigate('/pets');
                } else {
                    // We'll come back to this
                }
            })
            .catch(console.error);
    }

    useEffect(() => {
        if (petId) {
            fetch(`http://localhost:8080/api/pets/${petId}`)
                .then(res => {
                    if (res.ok) {
                        return res.json();
                    } else {
                        return Promise.reject(
                            new Error(`Unexpected status code ${res.status}`)
                        );
                    }
                })
                .then(setPet)
                .catch(error => {
                    console.error(error);
                    navigate('/pets');
                });
        }
    }, [petId]);


    if (!pet) {
        return null;
    }

    return (
        <div>
            <h1>Delete Confirmation</h1>
            <p>Are you sure you want to delete the following pet? </p>
            <ul>
                <li>Name: {pet.name}</li>
                <li>Type: {pet.type}</li>
                <li>Breed: {pet.breed || 'Unknown'}</li>
                <li>DOB: {pet.dob ?? 'Unknown'}</li>
            </ul>
            <div>
                <button onClick={handleDelete} className='btn btn-danger me-2'>
                    Delete
                </button>
                <Link className='btn btn-danger me-2' to='/pets'>
                    Cancel
                </Link>
            </div>
        </div>
    );
}
