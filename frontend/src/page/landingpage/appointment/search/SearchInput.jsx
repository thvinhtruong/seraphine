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
  const [url, setUrl] = useState(`/api/v1/doctor/appointment/search/query?issue_cover=${searchProblem}&address=${searchPostalCode}&distance_to_user=${searchDistance}`)

  useEffect(() => {
    fetch(url, {
      method: 'GET',
      mode: 'no-cors'
    })
      .then(response => {
        if (response.ok()) {
          return response.json()
        }
        throw response
      })
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
  }, [items])

  function handleSubmit(event) {
    event.preventDefault();
  }

  return (
    <React.Fragment>
      <div>
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
            <select type="search" value={searchDistance} onChange={(event) => {setSearchDistance(event.target.value)}}>
              <option value="10">10 km</option>
              <option value="50">50 km </option>
              <option value="100">100 km</option>
            </select>
          </Form.Group>
        </Form>
        <div>
            <Button className="search-button" onClick={}>
              Search
            </Button>
          </div>
      </div>
    </React.Fragment>
  );
}

export default SearchInput;
