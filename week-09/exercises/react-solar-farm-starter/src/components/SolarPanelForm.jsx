// eslint-disable-next-line react/prop-types
import {Link, useNavigate, useParams} from "react-router-dom";
import {useState, useEffect} from "react";
const inital_solarPanel = {
    id: 0,
    section: '',
    row: 0,
    column: 0,
    yearInstalled: 0,
    material: '',
    tracking: false,
}


export default function SolarPanelForm({handleAddSolarPanel}) {
    const [solarPanel, setSolarPanel] = useState(inital_solarPanel);
    const navigate = useNavigate();
    const {id} = useParams();
    useEffect(() => {
        if(id) {
            fetch(`http://localhost:8080/api/solar-panel/${id}`)
                .then(res => res.json())
                .then(setSolarPanel)
                .catch(console.error);
        }
    }, [id]);
    function doCreate(){
        fetch('http://localhost:8080/api/solar-panel', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(solarPanel),
        })
            .then(data => {
                if(data.ok){
                    navigate('/solar-panels');
                }else if (data.status === 400){
                    console.log(data)
                }else {
                    return Promise.reject(
                        new Error(`Unable to create solar panel: ${data.status} ${data.statusText}`)
                    );
                }
            })
            .catch(console.error);
    }
    function doUpdate(){
        fetch(`http://localhost:8080/api/solar-panel/${solarPanel.id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(solarPanel),
        })
            .then(data => {
                if(data.ok){
                    navigate('/solar-panels');
                }else if (data.status === 400){
                    console.log(data)
                }else if(data.status === 404) {
                    console.log('Solar Panel not Found');
                }else{
                    return Promise.reject(
                        new Error(`Unable to create solar panel: ${data.status} ${data.statusText}`)
                    );
                }
            })
            .catch(console.error);
    }


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
        // handleAddSolarPanel(solarPanel);
        // navigate('/solar-panels');
        if(solarPanel.id > 0){
            doUpdate();
        } else {
            doCreate()
            console.log('Submitted:', solarPanel);
        }
    }



    return <div className='container'>
        <form onSubmit={handleSubmit}>
            <h1>{solarPanel.id > 0 ? 'Update' : 'Add a Panel'}</h1>
            <div className='mb-3'>
                <label className='form-label' htmlFor='section'>Section</label>
                <input className='form-control' type='text' id={'section'} name ='section' value={solarPanel.section} onChange={handleChange}/>
            </div>
            <div className='mb-3'>
                <label className='form-label'>Row</label>
                <input className='form-control' type='number' id={'row'} name={'row'} value={solarPanel.row} onChange={handleChange}/>
            </div>
            <div className='mb-3'>
                <label className='form-label'>Column</label>
                <input className='form-control' type='number'  id={'column'} name={'column'} value={solarPanel.column} onChange={handleChange} />
            </div>
            <div className='mb-3'>
                <label className='form-label'>Year Installed</label>
                <input className='form-control' type='number' id={'yearInstalled'} name={'yearInstalled'} value={solarPanel.yearInstalled} onChange={handleChange} />
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
                    <option value='POLY_SI'>Poly-Silicon</option>
                    <option value='CIGS'>CIGS</option>
                    <option value='CD_TE'>CD-TE</option>
                    <option value='MONO_SI'>Mono-Silicon</option>
                </select>

            </div>
            <div className='mb-3'>
                <label className='form-check-label' htmlFor= 'tracking'>Tracking Software </label>
                <input className='form-check-input' type='checkbox' name= 'tracking' id="tracking" checked={solarPanel.tracking} onChange={handleChange} />
            </div>
            <div className='mb-3'>
            <button type='submit' className='btn btn-primary me-2'>Submit</button>
                <Link type= 'btn' className='btn btn-secondary' to={'/solar-panels'}>Cancel</Link></div>
        </form>
    </div>
}