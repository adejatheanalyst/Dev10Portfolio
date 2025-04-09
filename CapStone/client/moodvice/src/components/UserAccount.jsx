
import { useState, useEffect} from "react"
import { useNavigate } from "react-router-dom"
import { Code, Editable, Stack, useEditable, Button, Input, Grid, FileUpload, Icon, Box} from "@chakra-ui/react"
import {
    Field,
    Fieldset,
    For,
    NativeSelect,
    Textarea,} from "@chakra-ui/react"
export default function UserAccount({loggedIn, setLoggedIn}){
    const [user, setUser] = useState({
        username: loggedIn.username,
        email: loggedIn.email,
        password: loggedIn.password
    })

    const [errors, setErrors] = useState([])
    const [isEditing, setIsEditing] = useState(false)
    const navigate = useNavigate()
    const [file , setFile] = useState(null)
    const handleFileChange = (event) => {
        setFile(event.target.files[0]);
    };
    const handleFileUpload =  async () => {
        if(!file) return;
        const formData = new FormData();
        formData.append("file", file);

        const response = await fetch(`http://localhost:8080/api/user/${loggedIn.userId}/profileImg`, {
            method: "POST",
            body: formData,
            headers: {
                Authorization: loggedIn.userId
            }
        });

        const data = await response.json();
        console.log("Uploaded: ", data);
    }


            function handleUpdate(event){   
                setIsEditing(true);
                }
function handleSubmit(evt){
    evt.preventDefault()
    fetch(`http://localhost:8080/api/user/${loggedIn.userId}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
            Authorization: loggedIn.userId
        },
        body: JSON.stringify(loggedIn)
        
    })
    .then(res => {
        if (res.status === 401) {
            setLoggedIn(null);
            localStorage.clear("loggedIn");
        }
        return res.json();
    })
    .then(fetchedUser => {
        setLoggedIn(fetchedUser);
        console.log(fetchedUser);
        localStorage.clear("loggedIn");
        localStorage.setItem("loggedIn", JSON.stringify(fetchedUser));
        navigate("/myAccount");
        setErrors([]);
    })
    .catch(error => {
        setErrors([error.message]);
    })
    .finally(() => {
        setIsEditing(false);
    });
}

function handleChange(event){
    let newValue = null
    if (event.target.type === "checkbox") {
        newValue = event.target.checked
    } else {
        newValue = event.target.value
    }

    setLoggedIn({ ...loggedIn, [event.target.name]: newValue })
}
function handleCancel(evt){
    setIsEditing(false);
    setLoggedIn(JSON.parse(localStorage.getItem("loggedIn")));
}

useEffect(() => {
    localStorage.setItem("loggedIn", JSON.stringify(loggedIn));
  }, [loggedIn]);

    return(
        <Grid templateColumns={{ base: 'repeat(1, 1fr)', md: 'repeat(2, 1fr)' }}
        gap={6}>
        <Fieldset.Root>
            <Fieldset.Root>
            <Fieldset.Legend>Username</Fieldset.Legend>
            <Input color={"white"} class="form-control" id="username" 
            placeholder="username" 
            value={loggedIn.username} 
            name="username" 
            aria-label="Disabled Input example" 
            disabled={!isEditing} onChange={handleChange}>
            </Input>
            </Fieldset.Root>

        <Fieldset.Root>
        <Fieldset.Legend>Email</Fieldset.Legend>
            <Input color={"white"} class="form-control" 
            id="email"
            type="text" 
            placeholder="no email set" 
            value={loggedIn.email} 
            name="email" 
            aria-label="Disabled Input example" 
            disabled={!isEditing}onChange={handleChange}>
            </Input>
        </Fieldset.Root>

        <Fieldset.Root>
        <Fieldset.Legend>Password</Fieldset.Legend>
            <Input color={"white"} class="form-control" 
            id="password" 
            type="password" 
            placeholder="password" 
            value={loggedIn.password} 
            name="password" 
            disabled={!isEditing} onChange={handleChange}>
            </Input>
            </Fieldset.Root>

            <FileUpload.Root maxW ="x1" maxFiles = {1} type="file" onChange={handleFileChange} >
            <FileUpload.HiddenInput />
            <FileUpload.Dropzone>
        <FileUpload.DropzoneContent>
          <Box>Drag and drop to upload profile picture. </Box>
          <Box color="fg.muted">.png, .jpg up to 5MB</Box>
        </FileUpload.DropzoneContent>
      </FileUpload.Dropzone>
      <FileUpload.List onChange={handleChange}onUpload={handleFileUpload}/>
    </FileUpload.Root>




            <Button colorPalette="purple" variant="surface" onClick={isEditing ? handleSubmit : handleUpdate}>
                {isEditing ? "Save Changes" : "Update Account"}</Button>
                <>
                        
                                            
                </>
                </Fieldset.Root>
        </Grid>
    )
}