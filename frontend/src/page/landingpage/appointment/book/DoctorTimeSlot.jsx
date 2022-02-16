import React, { useState, useEffect} from 'react';
import {Card} from "react-bootstrap";


const DoctorTimeSlot = () => {
    const [error, setError] = useState(null);
    const [isLoaded, setIsLoaded] = useState(false);
    const [appointments, setAppointments] = useState([]);
    const [id, setId] = useState("");
    
    useEffect(() => {
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
    });

    return (
        <div>
            {appointments.map(item => (
                <ol key={item.id}>
                    <Card style={{width: '18rem', align: 'center'}} body>
                        Date: {item.dateBooking}
                        Time: {item.start_time} - {item.end_time}
                        Status: {() => {if (item.booked) {
                            return "booked"
                        }}}
                    </Card>
                </ol>
            ))}
        </div>  
    );
}

export default DoctorTimeSlot;