
import { useState} from "react";
import Calendar from 'react-calendar';
import UserMoodsGraph from './UserMoodsGraph';
import styled from 'styled-components';
import { Flex, Card, Stack, Button } from "@chakra-ui/react"
import moodviceLogoAbbr from '../assets/monogram-16px.svg'
import moodviceLogo from '../assets/moodvice-transparent.png'
import Resources from "./Resources";
import { motion } from "framer-motion";

export default function MoodCharts({loggedIn, setloggedIn}){
  const [value, onChange] = useState(new Date());
  const[userMoods, setfetchedMoods] = useState({})
  const[errors, setErrors] = useState([])
  const formattedDate = value.toISOString().split("T")[0];

            const handleClick = () =>{
                fetch(`http://localhost:8080/api/userMood/myMoods/${formattedDate}`, {
                    method: "GET",
                    headers: {
                        Authorization: loggedIn.userId
                    }
                })
                .then(res => {
                    if(res.status === 401) {
                        setLoggedIn(null)
                        localStorage.clear("loggedIn")
                    }
                    res.json()
                })
                .then(fetchedUserMoods => {
                  console.log(fetchedUserMoods)
                    setfetchedMoods(fetchedUserMoods)
                    
                })
                .then(fetchErrors => {
                    setErrors(fetchErrors)
                    console.log(errors)
                })
          }


function handleChange(value){
  onChange(value)
  handleClick()
}
const CalenderContainer = styled.div`
max-width: 600px;
  margin: auto;
  margin-top: 20px;
  background-color: #d4f7d4;
  padding: 10px;
  border-radius: 3px`
  ;
  

  

return(
    <div >
  <div >
  <div class="header d-flex align-items-center p-3 my-3 text-white bg-purple rounded shadow-sm">
  <img class="me-3" src={moodviceLogoAbbr} alt="" width="48" height="38"/>
    <div class="lh-1">
      <h1 class="h6 mb-0 text-white">Your Charts</h1>
      <small>Track your moods throughout the day</small>
    </div>
  </div>
  </div>

    <Stack gap ="100px" direction="row" className="moodChartsContainer">
    <motion.div
                initial={{ opacity: 0 }}
                animate={{ opacity: 1 }}
                transition={{ duration: 1 }}
                whileHover={{ scale: 1.05, boxShadow: "0px 4px 10px rgba(0, 0, 0, 0.2)" }} > 
            <Card.Root width={"400px"} className="calender" borderWidth={"3px"}> 
            <Card.Header></Card.Header>
            <Card.Body gap="2">
                <div>
                    <Calendar 
                        onChange={handleChange}
                        value={value} />
                    </div> 
                </Card.Body>
            <Card.Footer />
        </Card.Root>
        </motion.div>
                <motion.div
                initial={{ opacity: 0 }}
                animate={{ opacity: 1 }}
                transition={{ duration: 1 }} 
                whileHover={{ scale: 1.05, boxShadow: "0px 4px 10px rgba(0, 0, 0, 0.2)" }}> 
        <Card.Root width={"auto"} className="graphCard" borderWidth={"5px"}>
        <div class="moodChartTitle">
        <Card.Header >MoodCounts</Card.Header></div>
        <Card.Body>
                <UserMoodsGraph loggedIn={loggedIn} formattedDate={formattedDate} />
        </Card.Body>
        <Card.Footer/>  
        </Card.Root>
        </motion.div>
    </Stack>
    <div className="resources">
        <Resources/>
    </div>
    </div>
      )
}