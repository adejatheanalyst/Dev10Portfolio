import {useEffect, useState} from "react";
import {useNavigate, useParams} from "react-router-dom";
const inital_solarPanel = {
    id: 0,
    section: '',
    row: '',
    column: '',
    yearInstalled: '',
    material: '',
    tracking: false,
}


const SolarPanelsForm = (props) => {
    const [solarPanel, setSolarPanel] = useState(inital_solarPanel);
    const navigate = useNavigate();
    const  [errors, setErrors] = useState([]);
    const params = useParams()
    useEffect(() => {
        if(params.id === undefined){
            setSolarPanel(inital_solarPanel)
        }else {
            fetch(`http://localhost:8080/api/solar-panel/${params.id}`)
                .then(res => {
                    if(res.ok){
                    res.json().then(setSolarPanel)
                    }else{
                        navigate("/not-found")
                        console.log('Solar Panel not Found');
                    }
                })
                .catch(console.error);
        }
    }, [params.id]);

    function handleChange(event) {
        const updatedSolarPanel = {...solarPanel};
        if (event.target.type === 'checkbox') {
            updatedSolarPanel[event.target.name] = event.target.checked;
        } else {
            updatedSolarPanel[event.target.name] = event.target.value;
        }
        setSolarPanel(updatedSolarPanel);
        console.log(updatedSolarPanel)
    }
    function handleSubmit(event) {
        event.preventDefault();
        let method = 'POST';
        let url = "http://localhost:8080/api/solar-panel"
        if(params.id === undefined){
            method = "PUT"
            url += `/${params.id}`
        }
        fetch(url, {
            method,
            headers: {
                'Content-Type': 'application/json',
                Accept: 'application/json',
            },
            body: JSON.stringify(solarPanel),
        })
            .then(data => {
                if(data.ok){
                    navigate('/list');
                // }else if (data.status === 400){
                //     console.log(data)
                }else {
                    data.json().then(fetchedErrors => setErrors(fetchedErrors));
                    // return Promise.reject(
                    //     new Error(`Unable to create solar panel: ${data.status} ${data.statusText}`)
                    // );
                }
            })
            .catch(console.error);
    }
    return (
            <form onSubmit={handleSubmit}>
                <div className='mb-3'>
                    <label  htmlFor='section'>Section</label>
                    <input className='form-control' type='text' id={'section'} name={'section'} value = {solarPanel.section} onChange={handleChange} />
                </div>
                <div className='mb-3'>
                    <label className='form-label' htmlFor='row'>Row</label>
                    <input className='form-control' type='number' id={'row'} name={'row'} value={solarPanel.row} onChange={handleChange} />
                </div>
                <div className='mb-3'>
                    <label className='form-label' htmlFor= "column" >Column</label>
                    <input className='form-control' type='number'  id={'column'} name={'column'} value={solarPanel.column} onChange={handleChange} />
                </div>
                <div className='mb-3'>
                    <label className='form-label' htmlFor= "yearInstalled">Year Installed</label>
                    <input className='form-control' type='number' id={'yearInstalled'}  name={'yearInstalled'} value={solarPanel.yearInstalled} onChange={handleChange} />
                </div>
                <div className='mb-3'>
                    <label htmlFor='type' className='form-label'>Material
                    </label>
                    <select
                        name ='material'
                        id ='material'
                        className='form-select'
                        value={solarPanel.material}
                        onChange={handleChange}
                        required>
                        <option value='' disabled>
                            [Select a Material]
                        </option>
                        <option value='POLY_SI'>Multicrystalline Silicon</option>
                        <option value='CIGS'>Copper Indium Gallium Selenide</option>
                        <option value='CD_TE'>Cadmium Telluride</option>
                        <option value='MONO_SI'>Monocrystalline Silicon</option>
                    </select>

                </div>
                <div className='mb-3'>
                    <label className='form-check-label' htmlFor= 'tracking'>Tracking Software?:  </label>
                    <input className='form-check-input' type='checkbox' name= 'tracking' id="tracking" checked={solarPanel.tracking} onChange={handleChange} />
                </div>
                <div className='mb-3'>
                    <button type='submit' className='btn btn-primary me-2'>Submit</button>
                    <button type= 'btn' className='btn btn-secondary'>Cancel</button></div>
                {errors.length > 0 && (
                <div className='alert alert-danger'>
                        <h2>The following errors occurred:</h2>
                        <ul>
                            {errors.map(error => (
                                <li key={error}>{error}</li>
                            ))}
                        </ul>
                    </div>
                )}
            </form>
    )
}

export default SolarPanelsForm