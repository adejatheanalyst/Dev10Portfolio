import{Button, Flex, Container, Box, Text, HStack, Card, Stack, For, Avatar, Input} from "@chakra-ui/react";
import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

export default function ReplyList({ loggedIn, moodViceId }) {
    const [replies, setReplies] = useState([]);
   const[reply , setReply] = useState({title: "", body: "", moodId: ""})
   
    const [errors, setErrors] = useState([]);
    console.log(moodViceId)

    useEffect(() => {
        if(moodViceId === undefined){
            console.log("moodViceId is undefined")
            setReply({title: "", body: "", moodId: ""})
        }else{
        fetch(`${import.meta.env.VITE_APP_API_URL}/reply/moodVice/${moodViceId}`)
            .then(res => res.json())
            .then(fetchReplies => {
                setReplies(fetchReplies);
            })
            .then(err => {
                setErrors(err);
            });
        }
    }, [moodViceId]);
    
    const formatDate = (date) => {
        return new Date(date).toLocaleDateString("en-US", {
          month: "2-digit",
          day: "2-digit",
          year: "numeric",
        });
      };

    const handleChange = (evt) => {
        let newValue = null
    if (evt.target.type === "checkbox") {
        newValue = evt.target.checked
    } else {
        newValue = evt.target.value
    }
    setReply({...reply, [evt.target.name] : newValue })
    };

    const handleSubmit = (evt) => {
        evt.preventDefault();
        const replyData = {
            title: reply.title,
            body: reply.body,
            moodId: reply.moodId
        };
        fetch(`http://localhost:8080/api/reply/addReply/${moodViceId}`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                Authorization: loggedIn.userId
            },
            body: JSON.stringify(replyData)
        })
            .then(response => {
                if (response.ok) {
                    return response.json();
                } else {
                    return response.json().then(data => {
                        setErrors(data); 
                    });
                }
            })
            .then(data => {
                console.log(data);
                setReplies(prevReplies => [...prevReplies, data]);
            })
            .catch(errors => {
                setErrors(errors);
            });
    };

    return (
        <><Card.Body gap="2">
            {replies.length === 0 ? null : <Card.Title>Replies</Card.Title>}
            {errors > 0 && <ul id ="errors">
                {errors.map(error => <li key={error}>{error}</li>)}
                </ul>}
            
                {replies.map(reply => (
                   
                    <li key={reply.replyId}>
                        <Card.Description gap="2"> 
                        <h5 class="card-title">{reply.title} </h5>
                        <p >{reply.body}</p> 
                          <br />
                        </Card.Description>
                        <Card.Footer justifyContent="flex-start">
                        </Card.Footer>
                    </li>
                ))}
            <div>
                <Card.Root className="replyCard">
                <Stack gap="4" direction="row" wrap="wrap">
            <form onSubmit={handleSubmit}>
                <Input size = "sm"
                    type="text"
                    name="title"
                    placeholder="Title"
                    aria-label=".form-control-sm example"
                    value={reply.title}
                    onChange={handleChange}
                />
                <Input
                    type="text"
                    name="body"
                    placeholder="Body"
                    aria-label="default Input example"
                    value={reply.body}
                    onChange={handleChange}
                />
                <Card.Footer>
                <select className="replyCard text-bg-dark rounded"
                    aria-label="Small select example"
                    name="moodId"
                    value={reply.moodId}
                    onChange={handleChange}
                >
                    <option value="">Choose Mood</option>
                    <option value="1">Happy</option>
                    <option value="2">Sad</option>
                    <option value="3">Angry</option>
                    <option value="4">Anxious</option>
                </select>
                <Button size="sm" type="submit" colorPalette="purple" className="rounded p-2" variant="surface">Add Reply</Button>
                </Card.Footer>
            </form>
            </Stack>
            </Card.Root>
            </div>

            </Card.Body>
            </>
    );
}
