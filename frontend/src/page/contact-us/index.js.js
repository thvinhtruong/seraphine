import facebook from './Image/facebook.svg';
import mail from './Image/mail.svg';
import github from './Image/github.svg';
import {Col,
        Row,
        Container} 
from "react-bootstrap";
import './App.css';

function App() {
    return (
    <Container>
    <Row>
    <Col>
    <a className = "App-logocontact"
        href = "https://github.com/thvinhtruong/seraphine">
    <img src = { facebook }
            className = "App-logocontact"
            alt = "facebook"/>
    </a>
    <Col>
    </Col>
    <a className = "App-logocontact"
        href = "https://github.com/thvinhtruong/seraphine">
    <img src = { mail }
            className = "App-logocontact"
            alt = "mail"/>

    </a>
    <Col>
    </Col>
    <a className = "App-logocontact"
        href = "https://github.com/thvinhtruong/seraphine">
        <img src = { github }
            className = "App-logocontact"
            alt = "github"/>
    </a>
    </Col>
    </Row>
    </Container>
        );
    }
    
    export default App;