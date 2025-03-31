// src/PetForm.jsx

// col-12: full row (12/12 sections)
// col-8: 8/12 or 2/3 of the row
// col-3: 3/12 or 1/4 of the row

import {Link, useNavigate, useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import FormErrors from "./Forms/FormErrors.jsx";

const INITIAL_PET = {
    petId: 0,
    name: '',
    dob: '',
    type: '',
    breed: '',
    imageUrl: '',
    vaccinationStatus: 'UNKNOWN',
    adopted: false,
};


export default function PetForm() {
    // 2. Track an object, not properties
    const [pet, setPet] = useState(INITIAL_PET);
    const [errors, setErrors] = useState([]);
    const navigate = useNavigate();
    const {petId} = useParams();
    useEffect(() => {
        if (petId) {
            fetch(`http://localhost:8080/api/pets/${petId}`)
                .then(res => res.json())
                .then(setPet)
                .catch(console.error);
        }
    }, [petId]);

    function handleChange(event) {
        // . We never want to edit our `pet`.
        // Create a shallow clone with spread syntax.
        const updatedPet = {...pet};
        if (event.target.type === 'checkbox') {  // 1. Check box fields are outliers.
            // . Use the checked property of this input to determine
            // the property value.
            updatedPet[event.target.name] = event.target.checked;
        } else {
            // . Use the control's name to set the object's property.
            updatedPet[event.target.name] = event.target.value;
        }
        // . Replace the whole pet with the updated version.
        setPet(updatedPet);
        console.log(updatedPet);
    }

    function doCreate() {
        fetch(`http://localhost:8080/api/pets`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(pet),
        })
            .then(res => {
                if (res.ok) {
                    navigate(`/pets`);
                } else if (res.status === 400) {
                    // Log for now, we'll come back to this!
                    return res.json();
                } else {
                    //If the response code is anything else, reject promise and throw code execution to .catch()
                    return Promise.reject(
                        new Error(`Unexpected status code: ${res.status}`)
                    );
                }
            }).then(setErrors)
            .catch(console.error); // For now, just log any other types of errors
    }

    function doUpdate() {
        fetch(`http://localhost:8080/api/pets/${pet.petId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(pet),
        })
            .then(res => {
                if (res.ok) {
                    navigate(`/pets`);
                } else if (res.status === 400) {
                    // Log for now, we'll come back to this!
                    return res.json();
                    // console.log(res);
                } else if (res.status === 404) {
                    // Log for now, we'll come back to this!
                    // console.log('pet not found');
                    navigate('/404')
                } else {
                    // Any other status code is unexpected and thrown to .catch()
                    return Promise.reject(
                        new Error(`Unexpected status code: ${res.status}`)
                    );
                }
            }).then(setErrors)
            .catch(console.error); // For now, just log unexpected errors
    }


    function handleSubmit(event) {
        event.preventDefault();
        // console.log('Submitting:', pet);
        //onSubmit(pet); // 3. Call the onSubmit prop with the pet object.
        // . Reset the form after submission.
        // handleAddPet(pet);
        if (pet.petId > 0) {
            doUpdate();
        } else {
            doCreate()
            console.log('Submitted:', pet);
        }
    }
    return (
        <form onSubmit={handleSubmit}>
            <h1>{pet.petId > 0 ? 'Update' : 'Add Pet'}</h1>
            <div className='row'>
                <div className='col-12 col-md-8 mb-3'>
                    <label className='form-label' htmlFor='name'>
                        Name
                    </label>
                    {/* 6. Give inputs a value prop connecting to form state, and an onChange prop */}
                    <input
                        className='form-control'
                        type='text'
                        id='name'
                        name='name'
                        value={pet.name}
                        onChange={handleChange}
                    />
                </div>
                <div className='col-12 col-md-4 mb-3'>
                    <label htmlFor='dob' className='form-label'>
                        DOB
                    </label>
                    <input
                        className='form-control'
                        type='date'
                        id='dob'
                        name='dob'
                        value={pet.dob ?? ''}
                        onChange={handleChange}
                    />
                </div>
            </div>
            <div className='row'>
                <div className='col-12 col-md-4 mb-3'>
                    <label htmlFor='type' className='form-label'>
                        Type
                    </label>
                    <select
                        name='type'
                        id='type'
                        className='form-select'
                        value={pet.type}
                        onChange={handleChange}
                        required>
                        <option value='' disabled>
                            [Select a Type]
                        </option>
                        <option value='DOG'>Dog</option>
                        <option value='CAT'>Cat</option>
                        <option value='REPTILE'>Reptile</option>
                        <option value='RODENT'>Rodent</option>
                        <option value='BIRD'>Bird</option>
                        <option value='OTHER'>Other</option>
                    </select>
                </div>
                <div className='col mb-3'>
                    <label className='form-label' htmlFor='breed'>
                        Species/Breed
                    </label>
                    <input
                        className='form-control'
                        type='text'
                        id='breed'
                        name='breed'
                        value={pet.breed}
                        onChange={handleChange}
                    />
                </div>
            </div>
            <div className='col mb-3'>
                <label className='form-label' htmlFor='imageUrl'>
                    Image URL
                </label>
                <input
                    className='form-control'
                    type='url'
                    id='imageUrl'
                    name='imageUrl'
                    value={pet.imageUrl}
                    onChange={handleChange}
                />
            </div>
            <div className='row'>
                <div className='col-12 col-md-3 mb-3'>
                    <p>Vaccination Status</p>
                    <div className='form-check'>
                        <input
                            type='radio'
                            value='UP_TO_DATE'
                            className='form-check-input'
                            id='rdUpToDate'
                            name='vaccinationStatus'
                            checked={pet.vaccinationStatus === 'UP_TO_DATE'} /* 7. Check the radio button */
                            onChange={handleChange}
                        />
                        <label
                            className='form-check-label'
                            htmlFor='rdUpToDate'>
                            Up to Date
                        </label>
                    </div>
                    <div className='form-check'>
                        <input
                            type='radio'
                            value='NOT_UP_TO_DATE'
                            className='form-check-input'
                            id='rdNotUpToDate'
                            name='vaccinationStatus'
                            checked={pet.vaccinationStatus === 'NOT_UP_TO_DATE'} /* 8. Check the radio button */
                            onChange={handleChange}
                        />
                        <label
                            className='form-check-label'
                            htmlFor='rdNotUpToDate'>
                            Not up to Date
                        </label>
                    </div>
                    <div className='form-check'>
                        <input
                            type='radio'
                            value='UNKNOWN'
                            className='form-check-input'
                            id='rdUnknown'
                            name='vaccinationStatus'
                            checked={pet.vaccinationStatus === 'UNKNOWN'} /* 9. Check the radio button */
                            onChange={handleChange}
                        />
                        <label className='form-check-label' htmlFor='rdUnknown'>
                            Unknown
                        </label>
                    </div>
                </div>
                <div className='col mb-3'>
                    <p>Adoption Status</p>
                    <div className='form-check'>
                        <div className='form-check'>
                            <input
                                className='form-check-input'
                                type='checkbox'
                                name='adopted'
                                id='adopted'
                                checked={pet.adopted} /*check the checkbox*/
                                onChange={handleChange}
                            />
                            <label
                                className='form-check-label'
                                htmlFor='adopted'>
                                Adopted
                            </label>
                        </div>
                    </div>
                </div>
            </div>
            <div className='mb-3'>
                <button type='submit' className='btn btn-primary me-2'>
                    Submit
                </button>
                {/*<button type='btn' className='btn btn-secondary' onClick={() => setShowForm(false)}>*/}
                {/*    Cancel*/}
                {/*</button>*/}
                <Link className='btn btn-secondary' to='/'>Cancel</Link>
            </div>
            <FormErrors errors={errors}/>
            {/*{errors.length > 0 && (*/}
            {/*<div className='alert alert-danger'>*/}
            {/*        <h2>The following errors occurred:</h2>*/}
            {/*        <ul>*/}
            {/*            {errors.map(error => (*/}
            {/*                <li key={error}>{error}</li>*/}
            {/*            ))}*/}
            {/*        </ul>*/}
            {/*    </div>*/}
            {/*)}*/}
        </form>
    );
}