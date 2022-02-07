import './Login.css';
import React, { useState } from "react";
import {Form, 
        Button} from "react-bootstrap";

export default function Login() {
    const [email, setEmail] = useState("");
    
    function validateForm() {
        return email.length > 0
    }
    
    function handleSubmit(event) {
        event.preventDefault();
    }
    return (
        <div className="ForgetPassoword">
            <p className = "App-title">Forget Passoword</p> 
            <Form onSubmit={handleSubmit}>
                <Form.Group controlId="email">
                    <Form.Label>Email</Form.Label>
                    <Form.Control
                    autoFocus="true"
                    type="email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}/>
                </Form.Group>
                <Button block type="submit" disabled={!validateForm()}>
                Submit
                </Button>
            </Form>
        </div>
    )
}