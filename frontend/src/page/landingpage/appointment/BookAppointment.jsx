import React from 'react';
import DoctorMainPage from './doctor/DoctorMainPage';
import DoctorTimeSlot from './doctor/DoctorTimeSlot';
import BookButton from './doctor/BookButton';
import ReminderSelect from './doctor/ReminderSelect';

const BookAppointment = () => {
    return (
        <div>
            <DoctorMainPage />
            <h3 className="App-title">Appointment options</h3>
            <DoctorTimeSlot />
            <ReminderSelect />
            <BookButton />

        </div>
    );
}

export default BookAppointment; 