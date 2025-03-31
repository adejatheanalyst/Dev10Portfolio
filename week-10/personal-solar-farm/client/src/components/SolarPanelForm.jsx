import { useEffect, useState } from "react"
import { useNavigate, useParams } from "react-router-dom"

const SolarPanelsForm = ({ loggedInUser, setLoggedInUser }) => {

    const navigate = useNavigate()

    const params = useParams()

    const [errors, setErrors] = useState([])

    const INITIAL_SOLAR_PANEL = {
        section: "",
        row: "",
        column: "",
        yearInstalled: "",
        material: "",
        tracking: false,
    }

    const [solarPanel, setSolarPanel] = useState({})

    useEffect(() => {
        if (params.solarPanelId === undefined) {
            setSolarPanel(INITIAL_SOLAR_PANEL)
        } else {
            fetch(`http://localhost:8080/api/solarpanel/${params.solarPanelId}`)
            .then(response => {
                if (response.status >= 200 && response.status < 300) {
                    response.json().then(panel => setSolarPanel(panel))
                } else {
                    response.json().then((x) => {
                        debugger
                        
                    })
                    // setErrors(["Could not find that solar panel"])
                    navigate("/notFound")
                }
            })
        }
    }, [params.solarPanelId])


    const handleChange = (event) => {
        let newValue = null
        if (event.target.type === "checkbox") {
            newValue = event.target.checked
        } else {
            newValue = event.target.value
        }

        setSolarPanel({ ...solarPanel, [event.target.name]: newValue })
    }

    const handleSubmit = (event) => {
        event.preventDefault()
        let method = "POST"
        let url = "http://localhost:8080/api/solarpanel"
        if (params.solarPanelId !== undefined) {
            method = "PUT"
            url += `/${params.solarPanelId}`
        }

        fetch(url, {
            method,
            headers: {
                "Content-Type": "application/json",
                // Accept: "application/json",
                Authorization: loggedInUser.jwt
            },
            body: JSON.stringify(solarPanel)
        })
        .then(response => {
            if (response.status >= 200 && response.status < 300) {
                navigate("/list")
            } else if(res.status === 401) {
                setLoggedInUser(null)
                localStorage.clear("loggedInUser")
            }else {
                response.json().then(fetchedErrors => setErrors(fetchedErrors))
            }
        })
    }

    return (
        <div className="row">
            {errors.length > 0 && <ul id="errors">
                {errors.map(error => <li key={error}>{error}</li>)}
            </ul>}

            <h2>{params.solarPanelId ? `Editing panel ${solarPanel.section}-${solarPanel.row}-${solarPanel.column}` : "Add a new panel"}</h2>

            <div className="col-3"></div>
            <form onSubmit={handleSubmit} className="col-6">
                <div className="form-group">
                    <label htmlFor="section-input">Section: </label>
                    <input name="section" className="form-control" id="section-input" type="text" value={solarPanel.section} onChange={handleChange} />
                </div>

                <div className="form-group">
                    <label htmlFor="row-input">Row: </label>
                    <input name="row" className="form-control" id="row-input" type="number" value={solarPanel.row} onChange={handleChange}/>
                </div>

                <div className="form-group">
                    <label htmlFor="column-input">Column: </label>
                    <input name="column" className="form-control" id="column-input" type="number" value={solarPanel.column} onChange={handleChange}/>
                </div>

                <div className="form-group">
                    <label htmlFor="year-installed-input">Year Installed: </label>
                    <input name="yearInstalled" className="form-control" id="year-installed-input" type="number" value={solarPanel.yearInstalled} onChange={handleChange}/>
                </div>

                <div className="form-group">
                    <label htmlFor="material-input">Material: </label>
                    <select name="material" className="form-control" id="material-input" value={solarPanel.material} onChange={handleChange}>
                        <option value="">Pick a material...</option>
                        <option value="POLY_SI">Multicrystalline Silicon</option>
                        <option value="MONO_SI">Monocrystalline Silicon</option>
                        <option value="A_SI">Amorphous Silicon</option>
                        <option value="CD_TE">Cadmium Telluride</option>
                        <option value="CIGS">Copper Indium Gallium Selenide</option>
                    </select>
                </div>

                <div className="form-group">
                    <label htmlFor="is-tracking-input">Is tracking: </label>
                    <input name="tracking" className="" id="is-tracking-input" type="checkbox" checked={solarPanel.tracking} onChange={handleChange}/>
                </div>

                <button type="submit">{params.solarPanelId ? "Edit!" : "Add!"}</button>
            </form>
            <div className="col-3"></div>
        </div>
    )
}

export default SolarPanelsForm