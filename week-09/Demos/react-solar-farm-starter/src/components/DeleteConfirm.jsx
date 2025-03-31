import {useEffect, useState} from "react";
import { Link, useNavigate, useParams } from 'react-router-dom';
export default function SolarPanelDeleteConfirmation(){
    const [solarPanel, setSolarPanel] = useState(null);
    const { params} = useParams();
    console.log(params)
    const navigate = useNavigate();
    function handleDelete(){
        fetch(`http://localhost:8080/api/solar-panel/${id}`, {
            method: 'DELETE',
        })
            .then(data => {
                if(data.ok){
                    navigate('/list');
                }else {
                   navigate("/not-found")
                }
            })
            .catch(console.error);
    }
    useEffect(() => {
        if(params.id){
            fetch(`http://localhost:8080/api/solar-panel/${params.id}`)
                .then(data => data.json())
                .then(setSolarPanel)
                .catch(console.error);
        }
    }, [params.id]);
    if(!solarPanel){
        return null;
    }
    return (
        <div>
            <h1>Delete Confirmation</h1>
            <p>Are you sure you want to delete the following solar panel? </p>
            <ul>
                <li>Section: {solarPanel.section}</li>
                <li>Row: {solarPanel.row}</li>
                <li>Column: {solarPanel.column}</li>
                <li>Year Installed: {solarPanel.yearInstalled}</li>
                <li>Material: {solarPanel.material}</li>
                <li>Tracking: {solarPanel.tracking ? 'Yes' : 'No'}</li>
            </ul>
            <div>
                <button onClick={handleDelete} className='btn btn-warning me-2 mb-2'>
                    Delete
                </button>
                <Link className='btn btn-danger' to='/list'>
                    Cancel
                </Link>
            </div>
        </div>
    );
}