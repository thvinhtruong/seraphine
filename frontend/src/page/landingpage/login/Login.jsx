import './Login.css'
import React, { useState } from "react"
import {Link, Redirect} from "react-router-dom"
import {Form, Button} from "react-bootstrap";

export default function Login() {
  const [userName, setUserName] = useState();
  const [password, setPassword] = useState();
  const [error, setError] = useState("");
  const [token, setToken] = useState([]);

  const handleSubmit = async(event) => {
    const postData = {userName, password};
    if (userName === "" || password === "" ) {
      alert("Please enter all required fields");
      setError(true);
    } else {
      setError(false);
    }
    fetch(`/api/v1/login`, {
      method: 'POST',
      headers: {
        'Accept': 'application/json, text/plain',
        'Content-Type': 'application/json;charset=UTF-8'
      },
      body: JSON.stringify(postData)
    })
    .then((response) => {
      if (response.ok) {
        return response.json();
      }
      throw response;
    })
    .then((result) => {
      setToken(result);
      console.log(token);
    })
    .catch((error) => {
      setError(error);
      console.log(error);
    })
    event.preventDefault();
  }

  return (
    <div>
    <div className="Login">
      <Form>
        <p className = "App-title">LOGIN</p> 
        <Form.Group controlId="userName">
          <Form.Label>User Name</Form.Label>
          <Form.Control
            autoFocus
            type="userName"
            value={userName}
            onChange={(e) => setUserName(e.target.value)}/>
        </Form.Group>

        <Form.Group controlId="password">
          <Form.Label>Password </Form.Label>
          <Form.Control
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}/>
        </Form.Group>
          <Button block type="submit" onClick={handleSubmit}>
            LOGIN
          </Button>
          <p></p>
          <a href="/">Forgot your password</a>
      </Form>
      </div>
    </div>
  );
};
