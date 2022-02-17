import React, { useState} from 'react';
import {useParams} from 'react-router-dom';
import ReminderSelect from './ReminderSelect';
import {Form} from 'react-bootstrap';

const ConfirmPage = () => {
    const {id} = useParams();
    const [error, setError] = useState(null);
    const [isLoaded, setIsLoaded] = useState(false);
    const [message, setMessage] = useState([]);

    function handleSubmit(event) {
        fetch(`/api/v1/user/5/appointment/${id}/book`, {
            method: 'GET',
            mode: 'no-cors'
        })
        .then(response => {
            if (response.ok) {
                alert("appointment is booked successfully!");
                return response.json();
            }
            throw response;
        })
        .then((result) => {
            setIsLoaded(true);
            setMessage(result);
            console.log(message);
        }, 
        (error) => {
            setIsLoaded(true);
            setError(error);
        });
        event.preventDefault();
    }

    return (
        <div>
            <Form onSubmit={handleSubmit}>
                <h2>Do you want to book this appointment?</h2>
                <br></br>
                <button>Confirm</button>
                <br></br>
                <br></br>
                <a href={`https://localhost:8080/api/v1/user/5/appointment/123/generate_pdf`}>EXPORT PDF</a>
            </Form>
            <br></br>
            <ReminderSelect />

        </div>
    )

}

export default ConfirmPage;