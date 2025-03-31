import {use, useState} from 'react';
import {useNavigate} from 'react-router-dom';
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
    fetch('http://localhost:8080/api/user/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(user)
    })
    .then(response => {
        if (response.ok) {
            response.json().then(user =>{
                setLoggedIn(user)
                localStorage.setItem("loggedIn", JSON.stringify(user))
            navigate('/enterMood')
            }) 
        } else {
            response.json().then(data => {
                console.log(data)
                setErrors(data)
                console.log(errors)
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
    <div class="form-signin w-100 m-auto">
    <form onSubmit={handleSubmit}>
    <img class="mb-4" src={moodviceLogo} alt="" height="57"/>

    <div class="form-floating">
    <label htmlFor="floatingUsername" className='login-text'>Username</label>
     <input type="text" name="username" class="form-control" id="floatingUsername" value={user.username} onChange={handleChange}/>
     
    </div>
    <div class="form-floating">
     <input type="password" name="password" class="form-control" id="floatingPassword" placeholder="Password" value={user.password} onChange={handleChange}/>
      <label htmlFor="floatingPassword" className='login-text'>Password</label>
    </div>
 {errors.length > 0 && <ul id="errors">
             {errors.map(error => <li key={error}>{error}</li>)} </ul>}
    <button class="btn btn-primary w-100 py-2" type="submit">Sign In</button>
    <p class="mt-5 mb-3 text-body-secondary">© 2025</p>
  </form></div>

 
    )
}