import { Link, NavLink } from 'react-router-dom';
import logo from '../assets/logo.png';
import { useNavigate } from 'react-router-dom';


const NavBar = ({loggedInUser, setLoggedInUser}) => {
	const navigate = useNavigate()
    return (
        <header className='mb-3'>
				<nav className='navbar navbar-expand'>
					<div className='d-flex'>
						<NavLink className='navbar-brand' to='/'>
							<img src={logo} alt='Solar Farm' width='150' />
						</NavLink>
						<ul className='navbar-nav'>
							<li className='nav-item'>
								<NavLink className={(arg) => {
									if (arg.isActive) {
										return 'nav-link custom-active'
									} else {
										return 'nav-link'
									}
								}} to='/'>
									Home
								</NavLink>
							</li>
							<li className='nav-item'>
								<NavLink className={(arg) => {
									if (arg.isActive) {
										return 'nav-link custom-active'
									} else {
										return 'nav-link'
									}
								}} to='/'>
									About
								</NavLink>
							</li>
							<li className='nav-item'>
								<NavLink className={(arg) => {
									if (arg.isActive) {
										return 'nav-link custom-active'
									} else {
										return 'nav-link'
									}
								}} to='/'>
									Contact
								</NavLink>
							</li>
							<li className='nav-item'>
								<NavLink className={(arg) => {
									if (arg.isActive) {
										return 'nav-link custom-active'
									} else {
										return 'nav-link'
									}
								}} to="/list">View All Panels</NavLink>
							</li>
							{ loggedInUser === null?
							<>
							<li className='nav-item'>
								<NavLink className={(arg) => {
									if (arg.isActive) {
										return 'nav-link custom-active'
									} else {
										return 'nav-link'
									}
								}} to="/signup">Create an Account</NavLink>
							</li>
							<li className='nav-item'>
								<NavLink className={(arg) => {
									if (arg.isActive) {
										return 'nav-link custom-active'
									} else {
										return 'nav-link'
									}
								}} to="/login">Log In</NavLink>
							</li>
							</> : <>
							<li className='nav-item'>
							<button className='nav-link' onClick={()=> { setLoggedInUser(null); localStorage.clear(loggedInUser); navigate("/login") }}>Log out</button>
							</li>
							<li className='nav-item'>
								<NavLink className={(arg) => {
									if (arg.isActive) {
										return 'nav-link custom-active'
									} else {
										return 'nav-link'
									}
								}} to="/myPanels">View My Panels</NavLink>
							</li>
							<li className='nav-item'>
								<NavLink className={(arg) => {
									if (arg.isActive) {
										return 'nav-link custom-active'
									} else {
										return 'nav-link'
									}
								}} to="/add">Add a Panel</NavLink>
							</li>
							</>
							}
						</ul>
					</div>
				</nav>
			</header>
    )
}

export default NavBar