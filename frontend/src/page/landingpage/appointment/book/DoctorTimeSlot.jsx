import React, { useState, useEffect} from 'react';
import {useParams, Link} from 'react-router-dom';
import {Form} from "react-bootstrap";
import DoctorProfile from "../doctor/DoctorProfile";

const DoctorTimeSlot = () => {
    const [error, setError] = useState(null);
    const [isLoaded, setIsLoaded] = useState(false);
    const [appointments, setAppointments] = useState([]);
    const {id} = useParams();
    
    useEffect(() => {
        fetch(`/api/v1/doctor/appointment/${id}/all`, { 
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
    }, [id]);

    return (
        <div>
            <Form>
                <DoctorProfile id = {id} />
                {appointments.map(item => (
                    <Link to={`/book/appointment/5/${item.id}`}>
                        <div key={item.id}>
                            <button>
                                Date: {item.dateBooking}
                                <br></br>
                                Time: {item.start_time} - {item.end_time}
                                <br></br>
                            </button>
                        </div>
                    </Link>
                ))}
            </Form>
        </div>  
    );
}

export default DoctorTimeSlot;