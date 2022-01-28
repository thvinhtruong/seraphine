import React from 'react'
import "../../App.css"

//component
import Navbar from "./component/Navbar"
import SearchButton from "./page/landingpage/appointment/search/SearchButton"
import SearchInput from "./page/landingpage/appointment/search/SearchInput";

const LandingPage = () => {
    return (
        <div>
            <Navbar/>
            <SearchInput/>
            <SearchButton/>
        </div>
    )
}

export default Appointment
