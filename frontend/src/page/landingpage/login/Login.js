import './Login.css';
import React, { useState } from "react";
import {Form, 
        Button} from "react-bootstrap";

export default function Login() {
  const [userName, setUserName] = useState("");
  const [password, setPassword] = useState("");

  function validateForm() {
    return userName.length > 0 && password.length > 0;
  }

  function handleSubmit(event) {
    event.preventDefault();
  }

  return (
    <div className="Login">
      <p className = "App-title">LOGIN</p> 
      <Form onSubmit={handleSubmit}>
        <Form.Group controlId="userName">
          <Form.Label>User Name</Form.Label>
          <Form.Control
            autoFocus
            type="userName"
            value={userName}
            onChange={(e) => setUserName(e.target.value)}/>
        </Form.Group>

        <Form.Group  controlId="password">
          <Form.Label>Password </Form.Label>
          <Form.Control
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}/>
        </Form.Group>
        <Button block type="submit" disabled={!validateForm()}>
          LOGIN
        </Button>
        <p></p>
        <a href="#"> Forget password</a>

      </Form>
    </div>
  );
};
