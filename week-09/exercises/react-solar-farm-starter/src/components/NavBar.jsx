import logo from "../assets/logo.png";
import {NavLink} from "react-router-dom";

export default function NavBarSection(){
    return <div className='container'>
        <header className='mb-3'>
            <nav className='navbar navbar-expand'>
                <div className='d-flex'>
                    <NavLink className='navbar-brand' to='/'>
                        <img src={logo} alt='Solar Farm' width='150' />
                    </NavLink>
                    <ul className='navbar-nav'>
                        <li className='nav-item'>
                            <NavLink className='nav-link ' to='/'>
                                Home
                            </NavLink>
                        </li>
                        <li className='nav-item'>
                            <NavLink className='nav-link' to='/about'>
                                About
                            </NavLink>
                        </li>
                        <li className='nav-item'>
                            <NavLink className='nav-link' to='/contact'>
                                Contact
                            </NavLink>
                        </li>
                    </ul>
                </div>
            </nav>
        </header>
    </div>
}