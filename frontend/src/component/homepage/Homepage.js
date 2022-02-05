import React from 'react'
import {Link} from "react-router-dom"
import {Button} from "react-bootstrap"
import logo from '../../logo.svg'
import '../../App.css'

const RenderLogin = () => {
    return (
    <Button type="button" className = "App-button">
    <Link to="/page/landingpage/login/Login">Login</Link>
    </Button>
    )
}

const RenderRegister = () => {
    return (
        <Button type="button" className = "App-button">
            <Link to="/page/landingpage/register/Register">Register</Link>
        </Button>
    )
}

function Homepage() {
    return (
        <div className = "App-background">
        <div className = "App" >
            <header className = "App-header">
                <img src = { logo }
                    className = "App-logo"
                    alt = "logo" />
                <p className = "App-title">SERAPHINE</p> 
            <p> An E-Health app to make your life easier. Your personal healthcare assistant!</p>  
            {RenderLogin()}
            {RenderRegister()}
            </header>
        </div>
        </div>
    );
}

export default Homepage;
