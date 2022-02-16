import React from 'react';
import BookButton from './book/BookButton';
import ReminderSelect from './book/ReminderSelect';

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