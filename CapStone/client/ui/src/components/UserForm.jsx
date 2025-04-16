import {use, useState} from 'react';
import {useNavigate} from 'react-router-dom';
import { Code, Editable, Stack, useEditable } from "@chakra-ui/react"
import moodviceLogo from '../assets/moodvice-transparent.png'

export default function UserForm({setLoggedIn}) {
const navigate = useNavigate()
const [user, setUser] = useState({
    username: '',
    email: '',
    password: ''

})
const [errors, setErrors] = useState([])

const handleSubmit = (evt) => {
    evt.preventDefault()
    fetch(`${import.meta.env.VITE_APP_API_URL}/user`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    })
    .then(response => {
        if (response.ok) {
            response.json().then(user =>{
                setLoggedIn(user)
                localStorage.setItem("loggedIn", JSON.stringify(user))
                navigate('/myAccount')
            })
        } else if(response.status === 404) {
            response.json().then(data => {
                setErrors(data)
                console.log(data)
            })
        } else {
            response.json().then(data => {
                setErrors(data)
            })
        }
    })

}
const handleChange = (evt) => {
    setUser({
        ...user,
        [evt.target.name]: evt.target.value
    })
}

return(
    <div className='row'>
            <div class="form-signin w-100 m-auto">
            <form onSubmit={handleSubmit}>
            <img class="mb-4" src={moodviceLogo} alt="" height="57"/>
            <div class="form-floating">
                <label htmlFor="floatingInput"  className='login-text'>Username</label>
              <input type="text" name="username" class="form-control" id="floatingInput" value={user.username} onChange={handleChange}/>
              
            </div>
            <div class="form-floating"><label htmlFor="floatingInput"  className='login-text'>Email</label>
              <input type="email" name="email" class="form-control" id="floatingInput" value={user.email} onChange={handleChange}/>
            </div>
            <div class="form-floating">
              <input type="password" name="password" class="form-control" id="floatingPassword" placeholder="Password" value={user.password} onChange={handleChange}/>
              <label htmlFor="floatingPassword"  className='login-text'>Password</label>
            </div>
            <div class="errors">
            {errors.length > 0 && <ul id="errors">
                     {errors.map(error => <li key={error}>{error}</li>)} </ul>}
            </div>
        
            <button class="btn btn-primary w-100 py-2" type="submit">Sign Up</button>
            <p class="mt-5 mb-3 text-body-secondary">© 2025</p>
          </form>
          </div>
          {errors.length > 0 && <ul id="errors">
            {errors.map(error => <li key={error}>{error}</li>)} </ul>}
    </div>
    )
}