import { useEffect, useState } from "react"
import { Link } from "react-router-dom"
import SolarPanelTable from "./SolarPanelTable"

const SolarPanelsList = ({ loggedInUser }) => {
    useEffect(() => {
		fetch("http://localhost:8080/api/solarpanel")
		.then(res => res.json()
		.then(fetchedPanels => {
            setHasFinishedFetching(true)
            setPanels(fetchedPanels)
        }))
	}, [])

	const [panels, setPanels] = useState([])
    const [hasFinishedFetching, setHasFinishedFetching] = useState(false)

    if (panels.length === 0) {
        if (hasFinishedFetching) {
            return (
                <div>There are no panels to show</div>
            )
        } else {
            return (
                null
                // this could be a loading screen or a spinnner placeholder instead
            )
        }
    }
    return (
        <SolarPanelTable panels={panels} loggedInUser={loggedInUser} />
    )
}
export default SolarPanelsList