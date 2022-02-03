import React from 'react'
import {Switch, Route} from "react-router-dom"
import Navbar from "./component/navbar/Navbar"
import Homepage from "./component/homepage/Homepage"
//page
import Login from "./page/landingpage/login/Login"
import Register from "./page/landingpage/register/Register"

import './App.css';

function App() {
    return (
    <div>
        <div className = "App-background"/>
        <Navbar/>
        <Homepage/>
        <Switch>
            <Route path="/page/landingpage/login/Login">
                <Login />
            </Route>

            <Route path="/page/landingpage/register/Register">
                <Register/>
            </Route>
                    
            <Route path="/"></Route>
        </Switch>
        </div>
    );
}

export default App;