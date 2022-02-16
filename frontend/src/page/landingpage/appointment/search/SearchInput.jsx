import React, { useState } from "react"
import {Link} from 'react-router-dom'
import {Form, Button, Card} from "react-bootstrap";

const SearchInput = (props) => {
  const [searchProblem, setSearchProblem] = useState("");
  const [searchDistance, setSearchDistance] = useState("");
  const [searchPostalCode, setSearchPostalCode] = useState("");

  const [error, setError] = useState(null);
  const [isLoaded, setIsLoaded] = useState(false);
  const [items, setItems] = useState([]);
  const [appointments, setAppointments] = useState([]);
  const [id, setId] = useState("");
  const [isOpen, setIsOpen] = useState(false);
  const [slot, setSlot] = useState();

  const togglePopup = () => {
    setIsOpen(!isOpen);
  }

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
        },
        (error) => {
          setIsLoaded(true);
          setError(error);
        }
      );
      event.preventDefault();
  }

  function handleSearch(event) {
    fetch(`api/v1/doctor/appointment/${id}/all`, { 
        method: 'GET', 
        mode:'no-cors'
    })
    .then(response => {
        if (response.ok) {
            return response.json()
        } 
        throw response
    })
    .then(
        (result) => {
          setIsLoaded(true);
          setAppointments(result);
        },
        (error) => {
          setIsLoaded(true);
          setError(error);
        }
    );
    event.preventDefault();
  }

  const displayPopup = () => {
    return (
      <div>
        <Form onSubmit={handleSearch}>
          <Button variant="primary" type='submit' onClick={togglePopup}>select</Button>
        </Form>
        <div>
          {appointments.map(appoint =>
            {isOpen && <Popup content={<>
                <b>Choose your suitable time</b>
                <ol key={appoint.id}>
                  <h3>{appoint.dateBooking}</h3>
                  <select type='search' value={slot} onChange={(event) => {setSlot(event.target.value)}}>
                    <option value={appoint.id}>{appoint.start_time} - {appoint.end_time}</option>
                  </select>
                </ol>
                <Button variant="primary">Book now!</Button>
              </>}
              handleClose={togglePopup}
            />})}
        </div>
      </div>
    );
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
          <div>
            <Button className="search-button" type="submit">
              Search
            </Button>
          </div>

          <div>
            {items.map(item => (
                <Card style={{width: '30rem', align: 'center'}} key={item.id} border="primary" bg='light'>
                  <Card.Body>
                    <Card.Title>Dr {item.firstName + ' ' + item.lastName}</Card.Title>
                    <Card.Subtitle className="mb-2 text-muted">Specialization: {item.specialization}</Card.Subtitle>
                    <Card.Text>
                        Address: {item.address}
                        Treatment: {item.issue_covered}
                    </Card.Text>
                  </Card.Body>
                </Card>
            ))}
          </div>
        </Form>
      </div>
    </React.Fragment>
  );
}

export default SearchInput;
