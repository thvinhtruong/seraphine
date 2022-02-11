import React, { useState, useEffect } from "react"
import {Link} from 'react-router-dom'
import {Form, Button} from "react-bootstrap";

const SearchInput = () => {
  const [searchProblem, setSearchProblem] = useState("");
  const [searchDistance, setSearchDistance] = useState("");
  const [searchPostalCode, setSearchPostalCode] = useState("");

  const [error, setError] = useState(null);
  const [isLoaded, setIsLoaded] = useState(false);
  const [items, setItems] = useState([]);

  function validateForm() {
    return searchProblem.length > 0 && searchDistance.length > 0;
  }

  function handleSubmit(event) {
    event.preventDefault();
  }

  useEffect(() => {
    fetch(`localhost:8080/api/v1/doctor/appointment/search/query?issue_cover=${searchProblem}&address=${searchPostalCode}&distance_to_user=${searchDistance}`)
      .then(res => res.json())
      .then(
        (result) => {
          setIsLoaded(true);
          setItems(result);
        },
        (error) => {
          setIsLoaded(true);
          setError(error);
        }
      )
  }, [searchProblem, searchPostalCode, searchDistance])

  return (
    <div>
      <div className="search-input">
        <p className="App-title">Search Doctor</p>
        <Form onSubmit={handleSubmit}>
          <Form.Group controlId="searchProblem">
            <Form.Label>Health Problem</Form.Label>
            <Form.Control
                autoFocus 
                type="text" 
                onChange={(event) => {setSearchProblem(event.target.value);}}
                value={searchProblem}/>
          </Form.Group>

          <Form.Group controlId="searchPostalCode">
            <Form.Label>Postal Code</Form.Label>
            <Form.Control 
                autoFocus 
                type="text"
                onChange={(event) => {
                  setSearchPostalCode(event.target.value);
                }}
                value={searchPostalCode}/>
          </Form.Group>

          <Form.Group controlId="searchDistance">
            <Form.Label>Distance to current location</Form.Label>
            <select value="in km" onChange={(event) => {setSearchDistance(event.target.value)}}>
              <option value="10">10 km</option>
              <option value="50">50 km </option>
              <option value="100">100 km</option>
            </select>
          </Form.Group>

          <div>
            <Button
              className="search-btn search-btn-p" block type="submit" disabled={!validateForm()}>Search
            </Button>
          </div>
        </Form>
        <div>
          <ul>
            {items.map(item => (
              <li key={item.id}>
                {item.name} {item.price}
              </li>
            ))}
          </ul>
        </div>
      </div>
    </div>
  );
}

export default SearchInput;
