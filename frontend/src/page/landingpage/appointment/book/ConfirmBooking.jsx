import React from 'react';
import {Button, Form} from 'react-bootstrap';

const ConfirmBooking = () => {
    function handleSubmit(event) {
        event.preventDefault();
    }
    return (
        <div>
            <p className="App-title">Booking successfully</p>
            <Form onSubmit={handleSubmit}>
                <div>
                    <Button>export PDF File</Button>
                    <Button>View all appointment</Button>
                </div>   
            </Form>
        </div>
        
    )
}