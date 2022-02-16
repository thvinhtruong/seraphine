import React from 'react'
import {Switch, Route, Redirect} from "react-router-dom"
import Navbar from "./component/navbar/Navbar"
import Homepage from "./component/homepage/Homepage"
//page
import Login from "./page/landingpage/login/Login"
import Register from "./page/landingpage/register/Register"
import SearchAppointment from "./page/landingpage/appointment/SearchAppointment"
import BookAppointment from "./page/landingpage/appointment/BookAppointment"
import ConfirmBooking from './page/landingpage/appointment/ConfirmBooking'

function App() {
    return (
        <div className = "App-background">
            <Navbar/>
            <Switch>
                <Route path="/page/login">
                    <Login className="App-background"/>
                </Route>

                <Route path="/page/register">
                    <Register className="App-background"/>
                </Route>    

                <Route path="/page/search/appointment">
                    <SearchAppointment className="App-background"/>
                </Route>

                <Route exac path="/book/doctor/:id">
                    <BookAppointment/>
                </Route>

                <Route exac path="/book/appointment/:user_id/:appointment_id">
                    <ConfirmBooking/>
                </Route>

                <Route path="/">
                    <Homepage className="App-background"/> 
                </Route>
            </Switch>
        </div>
    );
}

export default App;