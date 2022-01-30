import React from 'react'
import {Switch, Route, Link} from "react-router-dom"
import {Button} from "react-bootstrap"
import Navbar from "./component/navbar/Navbar"

//page
import logo from './logo.svg'
import Login from "./page/landingpage/login/Login"
import Register from "./page/landingpage/register/Register"

import './App.css';

const login = ({login}) => {
    <Button className = "App-button">
    <Button type="button" className = "App-button">LOGIN</Button>
    <Link to="/page/landingpage/login/Login"></Link>
    </Button>
}

const register = ({register}) => {
    <Button className = "App-button">
    <Button type="button" className = "App-button">REGISTER</Button>
    <Link to="/page/landingpage/register/Register"></Link>
    </Button>
}

function App() {
    return (
    <div>
        <Navbar />
        <div className = "App-background">
        <div className = "App" >
        <header className = "App-header" >
        <img src = { logo }
            className = "App-logo"
            alt = "logo" />
        <p className = "App-title">SERAPHINE</p> 
        <p> An E-Health app to make your life easier. Your personal healthcare assistant!</p>  

        <Switch>
            <Route path="/page/landingpage/login/Login">
            <Login login={login}/>
            </Route>

            <Route path="/page/landingpage/register/Register">
            <Register register={register}/>
            </Route>
        </Switch>

        <a className = "App-link"
            href = "https://github.com/thvinhtruong/seraphine"> Contact us! 
        </a>  
        </header>   
        </div >
    </div>
    </div>
    );
}

export default App;