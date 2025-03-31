import logo from "../assets/logo.png";
import {Link, NavLink} from "react-router-dom";


export default function Home(){
    return <div>
        <h1>Home</h1>
        <div className='home-brand'>
        <img src={logo} alt='Solar Farm' width='300' /></div>
        <div>
            <h2>Welcome to the Solar Panel Farm.</h2>
            <button>
                <Link className='mb-3 btn btn-primary' to='/solar-panels'>View Solar Panels</Link>
            </button>
        </div>
    </div>
}