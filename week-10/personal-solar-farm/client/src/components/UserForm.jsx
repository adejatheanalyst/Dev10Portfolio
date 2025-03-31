import { useState } from "react"
import { useNavigate } from "react-router-dom"
import {jwtDecode} from "jwt-decode"

const UserForm = ({ setLoggedInUser }) => {

    const navigate = useNavigate()

    const [user, setUser] = useState({
        username: "",
        password: ""
    })

    const [errors, setErrors] = useState([])

    const handleSubmit = (event) => {
        event.preventDefault()

        fetch("http://localhost:8080/api/user", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(user)
        })
        .then(response => {
            if (response.status >= 200 && response.status < 300) {
                response.json().then(fetchedUser => {
                    const user = jwtDecode(fetchedUser.jwt)
                    user.jwt = fetchedUser.jwt
                    console.log(user)
                    setLoggedInUser(user)
                    localStorage.setItem("loggedInUser", JSON.stringify(user))
                    navigate("/list")
                })
            } else {
                response.json().then(fetchedErrors => setErrors(fetchedErrors))
            }
        })

    }

    const handleChange = (event) => {
        setUser({ ...user, [event.target.name]: event.target.value })
    }

    return (
        <div className="row">
            {errors.length > 0 && <ul id="errors">
                {errors.map(error => <li key={error}>{error}</li>)}
            </ul>}

            <h2>Create an Account</h2>

            <div className="col-3"></div>
            <form onSubmit={handleSubmit} className="col-6">
                <div className="form-group">
                    <label htmlFor="username-input">Username: </label>
                    <input name="username" className="form-control" id="username-input" type="text" value={user.username} onChange={handleChange} />
                </div>

                <div className="form-group">
                    <label htmlFor="password-input">Password: </label>
                    <input name="password" className="form-control" id="password-input" type="text" value={user.password} onChange={handleChange} />
                </div>

                <div className="form-group">
                    <button className="btn btn-info" type="submit">Sign up!</button>
                </div>
            </form>
        </div>
    )
}

export default UserForm
