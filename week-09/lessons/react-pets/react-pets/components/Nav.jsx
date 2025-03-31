export default function Nav() {
    return (
        <nav>
            <div>
                <a href='/'>React Pets</a>
                <ul>
                    <li className='nav-item'>
                        <a href='/'>Home</a>
                    </li>
                    <li className='nav-item'>
                        <a className='nav-link' href='/pets'>Pets</a>
                    </li>
                    <li className='nav-item'>
                        <a className='nav-link' href='/about'>About</a>
                    </li>
                    <li className='nav-item'>
                        <a href='/'>Contact</a>
                    </li>
                    <li className='nav-item'>
                        <a href='/'>Sign Up</a>
                    </li>
                    <li className='nav-item'>
                        <a href='/'>Log In</a>
                    </li>
                </ul>
            </div>
        </nav>
    );
}
