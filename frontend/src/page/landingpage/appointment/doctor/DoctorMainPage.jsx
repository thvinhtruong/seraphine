import React, { useState} from 'react';
import {Card} from "react-bootstrap";

const DoctorMainPage = () => {
    const [error, setError] = useState(null);
    const [isLoaded, setIsLoaded] = useState(false);
    const [doctors, setDoctors] = useState([]);
    const [id, setId] = useState("");
    
    useEffect(() => {
        fetch(`api/v1/doctor/${id}`, { 
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
              setItems(result);
            },
            (error) => {
              setIsLoaded(true);
              setError(error);
            }
        );
    });

    return (
        <div>
            {items.map(item => (
                <Card style={{width: '30rem', align: 'center'}} key={item.id} border="primary" bg='light'>
                    <Card.Body>
                    <Card.Title>Dr {item.firstName + ' ' + item.lastName}</Card.Title>
                    <Card.Subtitle className="mb-2 text-muted">Specialization: {item.specialization}</Card.Subtitle>
                    <Card.Text>
                        Email: {item.email}
                        Gender: {item.gender}
                        Treatment: {item.issue_covered}
                    </Card.Text>
                    <Card.Link href="#">Learn More</Card.Link>
                    </Card.Body>
                </Card>
            ))}
        </div>  
    );
}

export default DoctorMainPage;