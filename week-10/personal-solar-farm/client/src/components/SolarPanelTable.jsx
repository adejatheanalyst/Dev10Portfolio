import { Link } from "react-router-dom"

const SolarPanelTable = ({ panels, loggedInUser }) => {

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
                        { loggedInUser !== null && solarPanel.userId === loggedInUser.userId ? 
                            <td>
                                <Link to={`/edit/${solarPanel.id}`} className='btn btn-warning me-2 mb-2'>
                                    Edit
                                </Link>
                                <Link to={`/delete/${solarPanel.id}`} className='btn btn-danger'>
                                    Delete
                                </Link>
                            </td>
                            : <td></td>
                        }
                    </tr>
                ))}
            </tbody>
        </table>
    )
}

export default SolarPanelTable