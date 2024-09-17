// src/components/UserForm/UserForm.js
import React, { useState } from 'react';
import './UserForm.css';
function UserForm({ onSubmit }) {
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    
    // Creating the user data object
    const userData = {
      name: name,
      email: email,
    };
    // Log the user data to the console
    console.log('User Submitted:', userData);

    // Pass the user data to the parent component (Home)
    onSubmit(userData);

    // Clear the form after submission
    setName('');
    setEmail('');
  };

  return (
    <form onSubmit={handleSubmit} className="user-form">
      <div>
        <label htmlFor="name">Name:</label>
        <input
          type="text"
          id="name"
          value={name}
          onChange={(e) => setName(e.target.value)}
          required
        />
      </div>
      <div>
        <label htmlFor="email">Email:</label>
        <input
          type="email"
          id="email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
        />
      </div>
      <button type="submit">Submit</button>
    </form>
  );
}

export default UserForm;
