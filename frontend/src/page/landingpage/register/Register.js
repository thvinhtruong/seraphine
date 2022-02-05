import { useState } from 'react';

export default function Form() {

// States for registration
const [firstName, setfirstName] = useState('');
const [lastName, setlastName] = useState('');
const [email, setEmail] = useState('');
const [password, setPassword] = useState('');
const [userName, setUserName] = useState('');
const [dateofbirth, setDateofbirth] = useState('');
const [insuranceName, setInsuranceName] = useState('');
const [insuranceType, setInsuranceType] = useState('')

// States for checking the errors
const [submitted, setSubmitted] = useState(false);
const [error, setError] = useState(false);

//Handling the fisrtName change
const handlefirstName = (e) => {
	setfirstName(e.target.value);
	setSubmitted(false);
}

//Handling the lastName change
const handlelastName = (e) => {
	setlastName(e.target.value);
	setSubmitted(false);
}

// Handling the userName change
const handleUserName = (e) => {
	setUserName(e.target.value);
	setSubmitted(false);
};

// Handling the email change
const handleEmail = (e) => {
	setEmail(e.target.value);
	setSubmitted(false);
};

// Handling the password change
const handlePassword = (e) => {
	setPassword(e.target.value);
	setSubmitted(false);
};

// Handling the Dateofbirth change
const handleDateofBirth = (e) => {
	setDateofbirth(e.target.value);
	setSubmitted(false);
}

// Handling the insuranceName change
const handleInsuranceName = (e) => {
	setInsuranceName(e.target.value);
	setSubmitted(false);
}

// Handling the insuranceType change
const handleInsuranceType = (e) => {
	setInsuranceType(e.target.value);
	setSubmitted(false);
}

// Handling the form submission
const handleSubmit = (e) => {
	e.preventDefault();
	if (firstName ==='' || lastName ==='' || userName === '' || email === '' || password === '' || dateofbirth === '' || insuranceName === '' || insuranceType === '') {
	setError(true);
	} else {
	setSubmitted(true);
	setError(false);
	}
};

// Showing success message
const successMessage = () => {
	return (
	<div
		className="success"
		style={{
		display: submitted ? '' : 'none',
		}}>
		<p className="App-title">USER SUCCESSFULLY REGISTER</p>
	</div>
	);
};

// Showing error message if error is true
const errorMessage = () => {
	return (
	<div
		className="error"
		style={{
		display: error ? '' : 'none',
		}}>
		<p className="App-notify">PLEASE ENTER ALL THE FIELDS</p>
	</div>
	);
};

return (
	<div className = "App-background">
		<div className="form">
		<p className = "App-title">REGISTRATION</p> 

		{/* Calling to the methods */}
		<div className="messages">
			{errorMessage()}
			{successMessage()}
		</div>

			<form>
				{/* Labels and inputs for form data */}
				<div>
					<label className="label">First Name</label>
					<input onChange={handlefirstName} className="input"
					value={firstName} type="text" />
				</div>
				<div>
					<label className="label">Last Name</label>
					<input onChange={handlelastName} className="input"
					value={lastName} type="text" />
				</div>
				<div>
					<label className="label">User Name</label>
					<input onChange={handleUserName} className="input"
					value={lastName} type="text" />
				</div>
				<div>
					<label className="label">Email</label>
					<input onChange={handleEmail} className="input"
					value={email} type="email" />
				</div>
				<div>
					<label className="label">Password</label>
					<input onChange={handlePassword} className="input"
					value={password} type="password" />
				</div>
				<div>
					<label className="label">Date of Birth</label>
					<input onChange={handleDateofBirth} className="input"
					value={dateofbirth} type="date" />
				</div>
				<div>
					<label className="label">Insurance Name</label>
					<input onChange={handleInsuranceName} className="input"
					value={insuranceName} type="text" />
				</div>
				<div>
					<label className="label">Insurance Type</label>
					<input onChange={handleInsuranceType} className="input"
					value={insuranceType} type="text" />
				</div>
				<div>
					<button className = "App-button"
					onClick={handleSubmit} className="btn" type="submit">SUBMIT
					</button>
				</div>
			</form>
		</div>
	</div>
);
}
