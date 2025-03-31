import { useState, useEffect } from "react"
import { useParams, Link, useNavigate } from "react-router-dom"

const DeleteSolarPanel = ({loggedInUser}) => {

    const params = useParams()

    const navigate = useNavigate()

    const [solarPanel, setSolarPanel] = useState({})

    useEffect(() => {
        fetch(`http://localhost:8080/api/solarpanel/${params.solarPanelId}`)
        .then(response => {
            if (response.status >= 200 && response.status < 300) {
                response.json().then(panel => setSolarPanel(panel))
            } else {
                // setErrors(["Could not find that solar panel"])
                navigate("/notFound")
            }
        })
    }, [])


    const handleDelete = () => {
        fetch(`http://localhost:8080/api/solarpanel/${params.solarPanelId}`, {
            method: "DELETE",
            headers: { Authorization: loggedInUser.jwt }
        })
        .then(_ => navigate("/list"))
    }


    return (
        <>
        <p>Are you sure you want to delete panel {params.solarPanelId}?</p>
        <p>Section: {solarPanel.section}</p>
        <p>Row: {solarPanel.row}</p>
        <p>Column: {solarPanel.column}</p>
        <p>Year Installed: {solarPanel.yearInstlaled}</p>
        <p>Material: {solarPanel.material}</p>
        <p>Is Tracking?: {solarPanel.tracking ? 'Yes' : 'No'}</p>
        <button onClick={handleDelete} className="btn btn-danger me-2 mb-2">Delete</button> 
        <Link to="/list" className="btn btn-info me-2 mb-2">Cancel</Link>
        </>
    )

}

export default DeleteSolarPanel
