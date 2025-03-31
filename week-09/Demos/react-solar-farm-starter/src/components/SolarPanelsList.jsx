import {useEffect, useState} from "react";
import {Link} from "react-router-dom";
const SolarPanelsList = () => {
    useEffect(() => { //get all solar panels from the server
        fetch("http://localhost:8080/api/solar-panel")
            .then(res => res.json())
            .then(fetchedPanels => setPanels(fetchedPanels))
            .catch(console.error);
    }, []);

    const [panels, setPanels] = useState([]);

    return (
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
            {panels.map(solarPanel => (
                <tr key={solarPanel.id}>
                    <td>{solarPanel.section}</td>
                    <td>{solarPanel.row}</td>
                    <td>{solarPanel.column}</td>
                    <td>{solarPanel.yearInstalled}</td>
                    <td>{solarPanel.material}</td>
                    <td>{solarPanel.tracking ? 'Yes' : 'No'}</td>
                    <td>
                        <Link to = {`/edit/${solarPanel.id}`} className='btn btn-warning me-2 mb-2'>
                            Edit
                        </Link>
                        <Link to = {`/delete/${solarPanel.id}`} className='btn btn-danger'>
                            Delete
                        </Link>
                    </td>
                </tr>
            ))}
        </tbody>
    </table>

    )
}

export default SolarPanelsList