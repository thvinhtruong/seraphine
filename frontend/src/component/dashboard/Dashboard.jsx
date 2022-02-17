import React from 'react'
import {Link} from "react-router-dom"
import {Button} from "react-bootstrap"
import logo from '../../logo.svg'
import '../../App.css'

const RenderSearchDoctor = () => {
    return (
        <Button type="button" >
            <Link to="/page/search/appointment" className="link-style">
                <span >Search Now!</span>
            </Link>
        </Button>
    )
}

function Dashboard() {
    return (
        <div>
            <div className = "App" >
                <div className = "App-header">
                    <img src = { logo }
                        className = "App-logo"
                        alt = "logo" />
                    <p className = "App-title">SERAPHINE</p> 
                    <p> An E-Health app to make your life easier. Your personal healthcare assistant!</p>  
                    {RenderSearchDoctor}
                </div>
            </div>
        </div>
    );
}

export default Dashboard;
