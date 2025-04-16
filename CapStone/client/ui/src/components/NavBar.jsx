import { NavLink } from 'react-router-dom'
import moodviceLogoAbbr from '../assets/monogram-16px.svg'
import { useNavigate } from 'react-router-dom';
import { Box,  Button, Stack, Text, HStack, Avatar} from '@chakra-ui/react';




export default function NavBar({loggedIn, setLoggedIn}) {
    const navigate = useNavigate();

    function handleSignUp(evt) {
        navigate('/signup')
    }
    function handleLogin(evt) {
        navigate('/login')
    }
    function handleMyAccount(evt) {
        navigate('/myAccount')
    }


return(
  
    <header className='mb-3'>
            <nav className="navbar navbar-expand-lg navbar-dark">
                <div className="container-fluid">
                <NavLink className="logo-react" to='/'>
                    <img src={moodviceLogoAbbr} alt="logo" width="100" height="50" class="d-inline-block align-text-top" />
                </NavLink>
                    <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                        <li className='nav-item'>
                            <NavLink className={(arg) => {
                                if (arg.isActive) {
                                    return 'nav-link custom-active'
                                } else {
                                    return 'nav-link'
                                }
                            }} to='/moodVice'>
                                MoodVice
                            </NavLink>
                        </li>
                        {loggedIn === null?
                            <>
                            
                            </> : <>
                            <li className='nav-item'>
                            <NavLink className={(arg) => {
                                if (arg.isActive) {
                                    return 'nav-link custom-active'
                                } else {
                                    return 'nav-link'
                                }
                            }} to='/charts'>
                                Mood Charts
                            </NavLink>
                            </li>
                            </>
                            }
                            <li className='nav-item'>
                            <NavLink className={(arg) => {
                                if (arg.isActive) {
                                    return 'nav-link custom-active'
                                } else {
                                    return 'nav-link'
                                }
                            }} to='/myAccount'>
                                Profile
                            </NavLink>
                            </li>
                    </ul>
                            {loggedIn === null?
                        
                            <>
                        <ul>
                            <HStack wrap="wrap" gap="6">
                        <li class="d-flex">
                            <Button size="lg" variant="surface" onClick={handleSignUp}>Sign Up</Button>
                        </li>
                        <li >
                            <Button size="lg" variant="surface" onClick={handleLogin}>Log In</Button>
                        </li></HStack>
                        </ul>
                        </> 
                        :<>
                        <ul>
                        <HStack wrap="wrap" gap="6">
                            <li>
                            <Avatar.Root size="lg">
                        <Avatar.Fallback name="Segun Adebayo" />
                        <Avatar.Image src="https://bit.ly/sage-adebayo" />
                            </Avatar.Root>
                            </li>
                        <li>
                            <Button colorPalette="purple"size="lg" variant="surface" onClick={() => {
                                setLoggedIn(null)
                                navigate('/')
                                localStorage.removeItem("loggedIn")
                            }}>Log Out</Button>
                        </li>
                        </HStack>
                        </ul>
                        
                        </>
                        }
                    
                </div>
            </nav>
        </header>
        )


}