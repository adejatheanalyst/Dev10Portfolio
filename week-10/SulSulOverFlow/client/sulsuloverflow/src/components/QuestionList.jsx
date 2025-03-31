import { useEffect, useState } from "react"
import QuestionTable from "./QuestionTable"


export default function QuestionList(){
    const [questions, setQuestions] = useState([])
    const [hasFinishedFetching, setHasFinishedFetching] = useState(false)


    useEffect(()=>{
        fetch("http://localhost:8080/api/question")
        .then(res => res.json()
    .then (fetchedQuestions =>{
        setHasFinishedFetching(true)
        setQuestions(fetchedQuestions)
    } ))
    },[])
    if (questions.length === 0){
        if(hasFinishedFetching){
            return(
                <div>There are no Questions to show</div>
            )
        }else{
            return(
                null
                //insert a loading screen here when styling
            )
        }
    }
    return (
    <QuestionTable questions={questions}/>
    )
}