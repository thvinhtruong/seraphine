import { useState } from 'react';
import { Redirect } from 'react-router-dom/cjs/react-router-dom.min';
import './Register.css';
export default function Form() {
	// States for registration
	const [firstName, setfirstName] = useState('');
	const [lastName, setlastName] = useState('');
	const [email, setEmail] = useState('');
	const [password, setPassword] = useState('');
	const [userName, setUserName] = useState('');
	const [dateofbirth, setDateofbirth] = useState('');
	const [insuranceName, setInsuranceName] = useState('');
	const [insuranceType, setInsuranceType] = useState('');

	// States for checking the errors
	const [submitted, setSubmitted] = useState(false);
	const [error, setError] = useState(false);
	const [items, setItems] = useState([]);

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
		const dataPost = {firstName, lastName, email, userName, password, dateofbirth, insuranceType, insuranceName}
		if (firstName ==='' || lastName ==='' || userName === '' || email === '' || password === '' 
		|| dateofbirth === '' || insuranceName === '' || insuranceType === '') {
			setError(true);
		} else {
			setSubmitted(true);
			setError(false);
		}
		
		fetch(`/api/v1/registration`, {
			method: 'POST',
			mode: 'no-cors',
			body: JSON.stringify(dataPost),
			header: "text/plain"
		})
		.then((response) => {
			if (response.ok) {
				return response.json();
			}
			throw response;
		})
		.then((result) => {
			setSubmitted(true);
			setItems(result);
			console.log(items);
		}).catch((error) => {
			setSubmitted(false);
			console.log(error);
		})
		e.preventDefault();
	};


	return (
		// <div className = "App-background">
		<div>
			<div className="form">
			<p className = "App-title">REGISTRATION</p> 

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
					value={userName} type="text" />
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
