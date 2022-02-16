import React, {useState} from 'react';
import {Button} from 'react-bootstrap';

const BookButton = () => {
    const [error, setError] = useState(null);
    const [isLoaded, setIsLoaded] = useState(false);
    const [message, setMessage] = useState([]);
    const [user_id, setUser_id] = useState("");
    const [appointment_id, setAppointment_id] = useState("");

    function handleSubmit(event) {
        fetch(`api/v1/user/${user_id}/appointment/${appointment_id}/book`, {
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
        <div>
            <Button onSubmit={handleSubmit} type='submit'>
                Book now!
            </Button>
        </div>
    )
}
export default BookButton;