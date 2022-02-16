import React, { useState } from "react"
import {Link} from 'react-router-dom'
import {Form, Button, Card} from "react-bootstrap";

const SearchInput = () => {
  const [searchProblem, setSearchProblem] = useState("");
  const [searchDistance, setSearchDistance] = useState("");
  const [searchPostalCode, setSearchPostalCode] = useState("");

  const [error, setError] = useState(null);
  const [isLoaded, setIsLoaded] = useState(false);
  const [items, setItems] = useState([]);
  const [appointments, setAppointments] = useState([]);
  const [isOpen, setIsOpen] = useState(false);
  const [slot, setSlot] = useState();

  function handleSubmit(event) {
    fetch(`/api/v1/doctor/search/query?issue_covered=${searchProblem}&address=${searchPostalCode}&distance_to_user=${searchDistance}`, {
      method: 'GET',
      mode: 'no-cors'
    })
      .then(response => {
        if (response.ok) { 
          return response.json();
        }
        throw response;
      })
      .then(
        (result) => {
          setIsLoaded(true);
          setItems(result);
          console.log(result);
        },
        (error) => {
          setIsLoaded(true);
          setError(error);
        }
      );
      event.preventDefault();
  }

  return (
    <React.Fragment>
      <div>
        <h1 className="App-title">Search Doctor</h1>
        <Form onSubmit={handleSubmit}>
          <Form.Group controlId="searchProblem">
            <Form.Label><b>Health Problem</b></Form.Label>
            <Form.Control
                autoFocus 
                type="text" 
                onChange={(event) => {setSearchProblem(event.target.value);}}
                value={searchProblem}/>
          </Form.Group>

          <Form.Group controlId="searchPostalCode">
            <Form.Label><b>Postal Code</b></Form.Label>
            <Form.Control 
                autoFocus 
                type="text"
                onChange={(event) => {
                  setSearchPostalCode(event.target.value);
                }}
                value={searchPostalCode}/>
          </Form.Group>

          <Form.Group controlId="searchDistance">
            <Form.Label><b>Distance to current location</b></Form.Label>
            <select type="search" value={searchDistance} onChange={(event) => {setSearchDistance(event.target.value)}}>
              <option value="10">10 km</option>
              <option value="50">50 km </option>
              <option value="100">100 km</option>
            </select>
          </Form.Group>
          <div>
            <Button className="search-button" type="submit">
              <b>Search</b>
            </Button>
          </div>

          <div>
            {items.map(item => (
              <Link to={`/book/doctor/${item.id}`}>
                <div key={item.id}>
                  <h2>Dr {item.firstName + " " + item.lastName}</h2>
                  <ol>
                    Address: {item.address}
                    <></>
                    Treatments: {item.issue_covered}
                  </ol>
                </div>
              </Link>
            ))}
          </div>
        </Form>
      </div>
    </React.Fragment>
  );
}

export default SearchInput;
