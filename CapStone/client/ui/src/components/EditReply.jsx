
import{Button, Flex, Container, Box, Text, HStack, Card, Stack, For, Avatar} from "@chakra-ui/react";
import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";

export default function EditReply({loggedIn}) {
    const params = useParams();
    const [reply, setReply] = useState({});
    const navigate = useNavigate();
    const [errors, setErrors] = useState([]);
    const [replyId, setReplyId] = useState(params.replyId);
    useEffect(() => {
        if (params.replyId === undefined) {
            console.log("replyId is undefined");

            fetch(`${import.meta.env.VITE_APP_API_URL}/reply/${params.replyId}`)
                .then(res => {
                    if (res.status >= 200 && res.status < 300) {
                        res.json().then(res => setReply(res));
                    } else {
                        res.json().then(errors => setErrors(errors));
                        navigate("/reply");
                    }
                })
        }
    }, [params.replyId]);
    console.log(reply);
    console.log(reply.title)
    console.log(replyId);

    
    const handleChange = (evt) => {
    let newValue = null
    if (evt.target.type === "checkbox") {
        newValue = evt.target.checked
    } else {
        newValue = evt.target.value
    }
    setReply({...reply, [evt.target.name] : newValue })
        }
        
    

    return (
        <Stack gap="4" direction="row" wrap="wrap">
        <div>
            <h1>{params.moodViceId ? "Edit Reply" :""}</h1>
            {errors.length > 0 && <ul id ="errors">
                {errors.map(error => <li key={error}>{error}</li>)}
                </ul>}

            <form>
            <div class="mb-3">
        <label for="inputTitle" class="form-label">Title</label>
        <input type="text" name="title" className="form-control" id="title" value={reply.title} onChange={handleChange}/>
            </div>
        <div class="mb-3">
        <label for="inputBody" class="form-label"></label>
        <textarea className="form-control" name="body" 
                id="body" 
                rows="3" 
                type="text"
                placeholder="Express your thoughts in a healthy way. And maybe get some well needed support." 
                value={reply.body} 
                onChange={handleChange}>
        </textarea>
        </div>
        </form>
        </div>
        </Stack>
    )

}