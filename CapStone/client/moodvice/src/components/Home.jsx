import { Link } from 'react-router-dom'
import moodviceLogo from '../assets/moodvice-transparent.png'
import{useNavigate} from 'react-router-dom'
import { Text, Button, Input, Group} from '@chakra-ui/react'
import { useState } from 'react'


export default function Home({loggedIn}) {
    const navigate = useNavigate();

    const handleClick = (evt) => {
        evt.preventDefault()
        navigate("/login")  
    }
    
        const [errors, setErrors] = useState([]);
    
    
        const [moodId, setMoodId] = useState([]);
        const [userMood, setUserMood] = useState([]);
        const[userId, setUserId] = useState([])
        const handleChange = (evt) =>{
            evt.preventDefault()
            const moodId = evt.target.value
           switch(moodId){
               case "Happy":
                   setMoodId("1")
                   break;
               case "Sad":
                   setMoodId("2")
                   break;
               case "Angry":
                   setMoodId(3)
                   break;
               case "Anxious":
                   setMoodId(4)
                   break;
                case "calm":
                setMoodId(5)
                break;
                case "humorous":
                    setMoodId(6)
                    break;
                case "fearful":
                    setMoodId(7)
                    break;
               default:
                   setMoodId(moodId, 0)
                   break;
           }
    
        };
    
        const handleSubmit = (evt) => {
            if(loggedIn === null){
                navigate("/login")
            }
            evt.preventDefault()
            fetch('http://localhost:8080/api/userMood', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    Authorization: loggedIn.userId
                },
                body: JSON.stringify(moodId)
            }).then(response => {
                if (response.ok) {
                    setUserMood(moodId)
                    navigate(`/${moodId}`)
                } else {
                    response.json().then(fetchedErrors => setErrors(fetchedErrors))
                    console.log(errors)
                    }
                })
            }


    return(
        <>
    <div className='homeContainer'>
        <div>
        <h1 class="homeTitle">Welcome to MoodVice</h1>
        </div>
        <div>
        <Link to="/signup">
        <img src={moodviceLogo} alt="Moodvice Logo" className="logo react"/>
        </Link>
        </div>
        <div>
        <Text textStyle="5xl" fontWeight="semibold">Find your mood, find your vice</Text>
        {loggedIn !== null ? null : <h1>Please login to enter mood.</h1>}
        <form onSubmit={handleSubmit}>
            <Group attached w="full" maxW="sm">
        <Input className='enterMood' placeholder="Enter Mood" size="md" name='moodId' value={moodId} onChange={handleChange}/>
        <Button size="md" variant="surface" type="submit">Search</Button>
        </Group>
        </form>
        {errors.length > 0 && <ul id="errors">
                {errors.map(error => <li key={error}>{error}</li>)}
            </ul>}
        </div>

    </div>
    </>
    )
}
