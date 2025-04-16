import { useEffect, useState } from "react"
import { useNavigate, useParams } from "react-router-dom"
import MoodViceTable from "./MoodViceTable"
import moodviceLogo from '../assets/moodvice-transparent.png'
import { Button } from "@chakra-ui/react"


export default function MoodViceList({loggedIn}) {
const [moodVices, setMoodVices] = useState([])
const [loading, setLoading] = useState(true)
const navigate = useNavigate()


useEffect(() => {
    fetch(`${import.meta.env.VITE_APP_API_URL}/moodVice`)
    .then(res => res.json()
    .then(fetchMoodVices => {
        setLoading(false)
        setMoodVices(fetchMoodVices.reverse())
    }))
}, [])
if(moodVices.length === 0){
    if(loading){
        return (<p>Loading...</p>

        )
    }else{
        return(<p>Loading...</p>
        )
    }
}

function handleClick(evt){
    evt.preventDefault()
    navigate("/addMoodVice")
}

return(
    <div>
        {}
        <div class="title-moodvice">
                <img src={moodviceLogo}/>
                <p>Share your mood and get some advice.</p>
                <Button  colorPalette="purple" variant="surface" onClick={handleClick}>Add MoodVice</Button> 
        </div>
        <MoodViceTable moodVices={moodVices} setMoodVices={setMoodVices}loggedIn={loggedIn}/>
        

</div>
)
}
