import React from 'react'
import {Link} from "react-router-dom"
import {Button} from "react-bootstrap"
import logo from '../../logo.svg'
import '../../App.css'

const RenderLogin = () => {
    return (
        <Button type="button">
            <Link to="/page/login" className="link-style">
                <span>Login</span>
            </Link>
        </Button>
    )
}

const RenderRegister = () => {
    return (
        <Button type="button" >
            <Link to="/page/register" className="link-style">
                <span >Register</span>
            </Link>
        </Button>
    )
}

function Homepage() {
    return (
        <div>
            <div className = "App" >
                <div className = "App-header">
                    <img src = { logo }
                        className = "App-logo"
                        alt = "logo" />
                    <p className = "App-title">SERAPHINE</p> 
                    <p> An E-Health app to make your life easier. Your personal healthcare assistant!</p>  
                    {RenderLogin()}
                    {RenderRegister()}
                </div>
            </div>
        </div>
    );
}

export default Homepage;
