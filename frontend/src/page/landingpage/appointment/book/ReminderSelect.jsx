import React, {useState} from "react";
import {Form, Button} from 'react-bootstrap';

const ReminderSelect = () => {
    const [appointment_id, setAppointment_id] = useState("");
    const [option, setOption] = useState("");
    const [items, setItems] = useState("");
    const [error, setError] = useState("");

    function handleSubmit(event) {
        fetch(`/api/v1/user/5/appointment/${appointment_id}/remind?${option}`, {
            method: 'GET',
            mode: 'no-cors'
        })
        .then((response) => {
            if (response.ok) {
                return response.json();
            } else {
                alert("reminder has been set!");
            }
            throw response;
        })
        .then((result) => {
            setItems(result);
          },
          (error) => {
            setError(error);
          }
        )
        event.preventDefault();
    }

    return (
        <div>
            <Form onSubmit={handleSubmit}>
                <Form.Group controlId="option">
                    <Form.Label>Do you want us to remind you before the appointment?</Form.Label>
                        <select type="search" value={option} onChange={(event) => {setOption(event.target.value)}}>
                            <option value="TEN MINUTES">10 minutes</option>
                            <option value="ONE HOUR">1 hour </option>
                            <option value="THREE DAYS">3 days</option>
                        </select>
                </Form.Group>

                <div>
                    <Button className="search-btn" type='submit' onClick={() => {
                        return "you are reminded";
                    }}>Remind me!</Button>
                </div>
            </Form>
        </div>
    )
}

export default ReminderSelect;