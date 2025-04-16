import Modal from "react-modal";
import { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";

Modal.setAppElement("#root");

export default function MoodViceModal({isOpen, onClose, moodVice, mode, loggedIn}){
    const [title, setTitle] = useState(moodVice?.title || "");
    const [body, setBody] = useState(moodVice?.body || "");
    const [errors, setErrors] = useState([]);
    const navigate = useNavigate();
    const params = useParams();
    const [selectedMoodVice, setMoodVice] = useState({ title: "",
        body: "",
        userId: "",
        moodId: ""})

    useEffect(()=>{
            if(params.moodViceId === undefined){
                console.log("moodViceId is undefined")
                setMoodVice({title: "", body: "", userId: loggedIn.userId, moodId: ""});
            }else{
                fetch(`${import.meta.env.VITE_APP_API_URL}/moodVice/${params.moodViceId}`)
                .then(response =>{
                    if(response.status >= 200 && response.status < 300){
                        response.json().then(res => setMoodVice(res))
                    }else{
                        response.json().then(errors => setErrors(errors))
                        navigate("/moodVice")
                    }
                })
            }
        }, [params.moodViceId])

        const handleChange =(evt)=>{
            let newValue = null
            if (evt.target.type === "checkbox") {
                newValue = evt.target.checked
            } else {
                newValue = evt.target.value
            }
            setMoodVice({...moodVice, [evt.target.name] : newValue })
        }
    
    
    const handleSave = () => {
        let method = "POST"
        let url = "http://localhost:8080/api/moodVice"
        if(moodVice.moodViceId !== undefined){
            method = "PUT"
            url += `/${moodVice.moodViceId}`
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
                onClose()
            }else if(response.status === 401){
                setLoggedIn(null)
                localStorage.clear("loggedIn")
            }else{
                response.json().then(fetchedErrors => setErrors(fetchedErrors))
            }
        })
    
    }

        function handleDelete(evt){
            evt.preventDefault()
            fetch(`http://localhost:8080/api/moodVice/${moodVice.moodViceId}`,{
              method: "DELETE",
              headers:{
                  Authorization:loggedIn.userId
              }
          })
          .then(_=> evt.preventDefault(),
          onclose(),
        )
      }

    return(
        <Modal isOpen={isOpen} onRequestClose={onClose} className="modal-style">
        {mode === "edit" ? (
          <>
            <h2>Edit MoodVice</h2>
            <input type="text" name="title" value={moodVice.title} onChange={handleChange} />
            <textarea  name="body" value={moodVice.body} onChange={handleChange}/>
            <button onClick={handleSave}>Save</button>
            <button onClick={onClose}>Cancel</button>
          </>
        ) : (
          <>
            <h2>Confirm Delete</h2>
            <p>Are you sure you want to delete this MoodVice?</p>
            <p><strong>Title:</strong> {selectedMoodVice.title}</p>
            <p><strong>Body:</strong> {selectedMoodVice.body}</p>
            <button onClick={handleDelete}>Confirm Delete</button>
            <button onClick={onClose}>Cancel</button>
          </>
        )}
      </Modal>
    )
}