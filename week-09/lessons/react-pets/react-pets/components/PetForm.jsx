// src/PetForm.jsx

// col-12: full row (12/12 sections)
// col-8: 8/12 or 2/3 of the row
// col-3: 3/12 or 1/4 of the row

export default function PetForm({setShowForm}) {
    return (
        <form>
            <h1>Add a Pet</h1>
            <div className='row'>
                <div className='col-12 col-md-8 mb-3'>
                    <label className='form-label' htmlFor='name'>
                        Name
                    </label>
                    <input
                        className='form-control'
                        type='text'
                        id='name'
                        name='name'
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
                <button type='btn' className='btn btn-secondary' onClick={() => setShowForm(false)}>
                    Cancel
                </button>
            </div>
        </form>
    );
}

