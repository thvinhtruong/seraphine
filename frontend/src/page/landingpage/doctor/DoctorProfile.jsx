import React, {useState, useEffect} from 'react';

const DoctorProfile = (props) => {
    const [profile, setProfile] = useState([]);
    const [isLoaded, setIsLoaded] = useState(False);
    const [error, setError] = useState("");
    const id = props.id;
    console.log(props, id);
    useEffect(() =>(
        fetch(`/api/v1/doctor/${id}`, { 
            method: "GET",
            mode: 'no-cors'
        })
        .then(response => {
            if (response.ok) {
                return response.json();
            }
        })
        .then((result) => {
            setIsLoaded(True)
            setProfile(result)
        }, 
        then (error => {
            setIsLoaded(True)
            setError(error)
        })
    ), [id]));
    
    return (
        <div>
            {profile.map(item => (
                <ol key={item.id}>
                    <li>Name: {item.firstName + ' ' + item.lastName}</li>
                    <li>Gender: {item.gender}</li>
                    <li>Email: {item.email}</li>
                    <li>Address: {item.address}</li>
                    <li>Specialization: {item.specialization}</li>
                    <li>Treatment: {item.issue_covered}</li>
                </ol>
            ))}
        </div>
    )
}

export default DoctorProfile;