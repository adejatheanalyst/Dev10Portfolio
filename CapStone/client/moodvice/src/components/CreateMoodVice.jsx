import { Input, Stack,Field, Button, Card} from "@chakra-ui/react";
import { Portal, Select, createListCollection } from "@chakra-ui/react"
import { useEffect, useState } from "react"
import { useNavigate, useParams } from "react-router-dom"
import ReplyList from "./ReplyList";
import MoodViceTable from "./MoodViceTable";
import MoodViceList from "./MoodViceList";
import moodviceLogo from '../assets/moodvice-transparent.png'
import { motion } from "framer-motion";




export default function({loggedIn, setLoggedIn}){
    const navigate = useNavigate();
    const params = useParams();
    const [errors, setErrors] = useState([]);
   const [moodVice, setMoodVice] = useState({ title: "",
    body: "",
    userId: "",
    moodId: ""})
    const[moodViceId, setMoodViceId] = useState(params.moodViceId)

    useEffect(()=>{
        if(params.moodViceId === undefined){
            console.log("moodViceId is undefined")
            setMoodVice({title: "", body: "", userId: loggedIn.userId, moodId: ""});
        }else{
            fetch(`http://localhost:8080/api/moodVice/${params.moodViceId}`)
            .then(response =>{
                if(response.status >= 200 && response.status < 300){
                    response.json().then(res => setMoodVice(res))
                }else{
                    response.json().then(errors => setErrors(errors))
                    console.log(errors)
                    navigate("/moodVice")
                }
            })
        }
    }, [params.moodViceId])


    
const handleChange =(evt)=>{
    let newValue = evt.target.value
    if (evt.target.type === "checkbox") {
        newValue = evt.target.checked
    } else  if(evt.target.name === "moodId"){
        newValue = parseInt(evt.target.value)
    }
    setMoodVice({...moodVice, [evt.target.name] : newValue })
}
const handleReset = (evt) =>{
    evt.preventDefault()
    setMoodVice({title: "", body: "", userId: loggedIn.userId, moodId: ""})
}
const handleCancel = (evt) =>{
    evt.preventDefault()
    navigate("/moodVice")
}
const handleSubmit = (evt) =>{
    evt.preventDefault()
    let method = "POST"
    let url = "http://localhost:8080/api/moodVice"
    if(params.moodViceId != undefined){
        method = "PUT"
        url += `/${params.moodViceId}`
    }
    fetch(url,{
      method,
      headers:{
        "Content-Type":"application/json",
        Authorization:loggedIn.userId
      },
      body: JSON.stringify(moodVice)  
    })
    .then(response =>{
        if(response.status >= 200 && response.status < 300){
            
            navigate("/moodVice")
        }else if(response.status === 401){
            setLoggedIn(null)
            localStorage.clear("loggedIn")
        }else{
            response.json().then(fetchedErrors => setErrors(fetchedErrors))
        }console.log(response.status)
    })

}
const frameworks = createListCollection({
    items: [
      { label: "Happy", value: "1" },
      { label: "Sad", value: "2" },
      { label: "Angry", value: "3 "},
      { label: "Anxious", value: "4" },
      { label: "Calm", value: "5" },
      { label: "humorous", value: "6" },
    ],
  })
    return(
        <div>

        <div className="form-group w-100 m-auto">
            <motion.div
                            initial={{ opacity: 0 }}
                            animate={{ opacity: 1 }}
                            transition={{ duration: 1 }}
                            whileHover={{ scale: 1.05, boxShadow: "0px 6px 30px rgba(128, 9, 248, 0.2)" }} >
            <Card.Root maxW="md" borderWidth="1px" borderRadius="lg" overflow="hidden" boxShadow="lg" bg="black" color="white">
            <h1 className="form-group-title" >{params.moodViceId ? "Edit MoodVice" :" Add MoodVice"}</h1>
            {errors.length > 0 && <ul id ="errors" class="errors">
                {errors.map(error => <li key={error}>{error}</li>)}
                </ul>}

            <form onSubmit={handleSubmit} >
            <div>
        <label class="title" >Title</label>
        <Input className ="input" type="text" name="title"  id="title" value={moodVice.title} onChange={handleChange}/>
            </div>
        <div>
        <label class="title" for="InputBody" >MoodVice Body</label>
        <Input size="lg" name="body" className="input" 
                id="body" 
                rows="3" 
                type="text"
                placeholder="Express your thoughts in a healthy way. And maybe get some well needed support." 
                value={moodVice.body} 
                onChange={handleChange}>
        </Input>
        <div>
        <label class="title" htmlFor="moodId-Input">Mood</label>
        <select className="input"
        name="moodId" 
        id="moodId" 
        aria-label="Default select example"
        value={moodVice.moodId}
        onChange={handleChange}
         >
            <option value = "" selected>Choose Mood</option>
            <option value="1">Happy</option>
            <option value="2">Sad</option>
            <option value="3">Angry</option>
            <option value="4">Anxious</option>
        </select>
            </div>
   
            <div>
            <Button type="submit"  colorPalette="purple" variant="surface">Submit</Button>
            <Button type="reset"  colorPalette="purple" variant="surface" onClick={handleReset}>Reset</Button>
            <Button type="cancel"  colorPalette="purple" variant="surface" onClick ={handleCancel}>Cancel</Button>
            </div>
            

            </div>
        </form>
        </Card.Root>
        </motion.div>
        </div>
        </div>
    )
}
