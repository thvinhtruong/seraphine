import React from 'react'
import {Switch, Route, Redirect} from "react-router-dom"
import Navbar from "./component/navbar/Navbar"
import Homepage from "./component/homepage/Homepage"
//page
import Login from "./page/landingpage/login/Login"
import Register from "./page/landingpage/register/Register"
import Appointment from "./page/landingpage/appointment/Appointment"

function App() {
    return (
        <div className = "App-background">
            <Navbar/>
            <Switch>
                <Route path="/page/landingpage/login/Login">
                    <Login className="App-background"/>
                </Route>

                <Route path="/page/landingpage/register/Register">
                    <Register className="App-background"/>
                </Route>    

                <Route path="/page/landingpage/appointment/Appointment">
                    <Appointment className="App-background"/>
                </Route>

                <Route path="/">
                    <Homepage className="App-background"/> 
                </Route>
            </Switch>
        </div>
    );
}

export default App;