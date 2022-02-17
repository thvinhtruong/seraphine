import React from 'react';

const ViewAppointment = () => {
    const [error, setError] = useState(null);
    const [isLoaded, setIsLoaded] = useState(false);
    const [items, setItems] = useState([]);

    const handleSubmit = (event) => {
        fetch(`api/v1/user/${user_id}/appointment/all`, {
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
    }

    return (
        <div>
            {items.map(item => (
                <div key={item.id}>
                    <h2>Appointment {item.id}</h2>
                    <ol>
                    date: {item.dateBooking}
                    <br></br>
                    Time: {item.start_time} - {item.end_time}
                    </ol>
                </div>
            ))}
        </div>
    )
}