import { NavLink } from 'react-router-dom'
import plumbob from '../assets/plumbob.png'


export default function TopNavBar(){

    return (
        <header className='mb-3'>
				<nav className="navbar navbar-expand-lg bg-body-tertiary">
					<div className="d-flex">
                    <NavLink class="navbar-brand" to='/'>
                        <img src={plumbob} alt="Logo" width="30" height="26" class="d-inline-block align-text-top" />
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
								}} to='/about'>
									About
								</NavLink>
                                </li>
                                <form class="d-flex" role="search">
                                <input class="form-control me-2" type="search" placeholder="SulSulOverFlow" aria-label="Search"/>
                                <button class="btn btn-outline-success" type="submit">Search</button>
                                </form>
							<li className='nav-item'>
								<NavLink className={(arg) => {
									if (arg.isActive) {
										return 'nav-link custom-active'
									} else {
										return 'nav-link'
									}
								}} to="/signup">Sign Up</NavLink>
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
						</ul>
					</div>
				</nav>
			</header>
            )
            }







{/* /* <nav className="navbar navbar-expand">
        <div className="d-flex">
    <NavLink class="navbar-brand" to='/'>
      <img src={plumbob} alt="Logo" width="30" height="26" class="d-inline-block align-text-top" />
      SulSulOverFlow
    </NavLink>
  </div>
</nav> */ }
