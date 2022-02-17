import './Login.css'
import React, { useState } from "react"
import {Link, Redirect} from "react-router-dom"
import {Form, Button} from "react-bootstrap";

export default function Login({setToken}) {
  const [userName, setUserName] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  async function handleSubmit(event) {
    const postData = {userName, password};
    fetch(`/user/login`, {
      method: 'POST',
      body: JSON.stringify(postData),
      headers: {
        'Accept': 'application/json, text/plain',
        'Content-Type': 'application/json;charset=UTF-8'
      }
    })
    .then((response) => {
      if (response.ok) {
        return response.json();
      }
      throw response;
    })
    .then((result) => {
      console.log(result);
    })
    .catch((error) => {
      setError(error);
      console.log(error);
    })
    event.preventDefault();
  }

  function validateForm() {
    if (userName.length === 0 || password.length === 0) {
      alert("Please enter all required fields");
    }
  }

  return (
    <div>
    <div className="Login">
      <Form onSubmit={handleSubmit}>
      <p className = "App-title">LOGIN</p> 
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
          <Link to={`/page/search/appointment`}>
            <Button block type="submit" onClick={validateForm}>
              LOGIN
            </Button>
          </Link>
          <p></p>
          <a href="/">Forgot your password</a>
      </Form>
      </div>
    </div>
  );
};
