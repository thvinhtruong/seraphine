import React, { useState} from 'react';
import {useParams} from 'react-router-dom';
import ReminderSelect from './ReminderSelect';

const ConfirmPage = (props) => {
    const {id} = useParams();
    const [error, setError] = useState(null);
    const [isLoaded, setIsLoaded] = useState(false);
    const [message, setMessage] = useState([]);

    function handleSubmit(event) {
        fetch(`api/v1/user/${props.user_id}/appointment/${id}/book`, {
            method: 'GET',
            mode: 'no-cors'
        })
        .then(response => {
            if (response.ok) {
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
            <h2>Do you want to book this appointment?</h2>
            <br></br>
            <button onClick={handleSubmit}>Confirm</button>
            <br></br>
            <h2>Do you want us to remind you?</h2>
            <ReminderSelect />

        </div>
    )

}

export default ConfirmPage;