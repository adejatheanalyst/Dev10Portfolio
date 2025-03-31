import {Link} from "react-router-dom";
import {useEffect, useState} from 'react';



// eslint-disable-next-line react/prop-types
export default function SolarTableSection() {
    const [solarPanels, setSolarPanels] = useState([]);

    useEffect(() => {fetch('http://localhost:8080/api/solar-panel')
        .then(res => res.json())// Read the body stream to completion
        .then(setSolarPanels)  // Put the body in pets state (shorthand for .then(res => setPets(res)))
        .catch(console.error);
    }, []);

    return <div>
        <h1 className='mb-3'>Solar Panels</h1>
        <Link className='mb-3 btn btn-primary' to ={'/solar-panels/add'}>Add a Solar Panel
        </Link>
        <table className='table table-striped'>
            <thead>
            <tr>
                <th>Section</th>
                <th>Row</th>
                <th>Column</th>
                <th>Year Installed</th>
                <th>Material</th>
                <th>Tracking Software</th>
                <th>Modify</th>
            </tr>
            </thead>
            <tbody>
            {solarPanels.map(solarPanel => (
                <tr key={solarPanel.id}>
                    <td>{solarPanel.section}</td>
                    <td>{solarPanel.row}</td>
                    <td>{solarPanel.column}</td>
                    <td>{solarPanel.yearInstalled}</td>
                    <td>{solarPanel.material}</td>
                    <td>{solarPanel.tracking ? 'Yes' : 'No'}</td>
                    <td>
                        <Link className='btn btn-warning me-2 mb-2' to={`/solar-panels/edit/${solarPanel.id}`}>
                            Edit
                        </Link>
                        <Link className='btn btn-danger' to={`/solar-panels/delete/${solarPanel.id}`}>
                            Delete
                        </Link>
                    </td>
                </tr>
            ))}
            </tbody>

        </table>
    </div>

}