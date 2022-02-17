import { Link } from 'react-router-dom';
import './Navbar.css';

function Navbar() 
{
    return (
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <Link className='nav-link' to="/">HOME</Link>
            </li>
            <li class="nav-item">
                <Link className='nav-link' to="/">HELP</Link>
            </li>
            <li class="nav-item">
                <Link className='nav-link' to="/">ABOUT US</Link>
            </li>
            <li class="nav-item">
                <Link className='nav-link' to="/">CONTACT US</Link>
            </li>
        </ul>
        </nav>
    );
}

export default Navbar;