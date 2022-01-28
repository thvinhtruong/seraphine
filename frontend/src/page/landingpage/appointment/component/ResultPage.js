import React from 'react'

//component
import Navbar from "../component/Navbar"
import ResultSearcBtn from '.page/landingpage/appointment/search/ResultSearcBtn'
import ResultSearchInput from '.page/landingpage/appointment/search/ResultSearch';

const ResultPage = () => {   
    return (
        <div>
            <Navbar/>
            <ResultSearchInput></ResultSearchInput>
            <ResultSearcBtn></ResultSearcBtn>
        </div>
    )
}

export default ResultPage
