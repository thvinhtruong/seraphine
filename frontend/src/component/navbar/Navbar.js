import './Navbar.css';

function Navbar() 
{
    return (
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="#">HOME<span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="">HELP </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">ABOUT US </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">CONTACT US </a>
            </li>
        </ul>
        </nav>
    );
}

export default Navbar;