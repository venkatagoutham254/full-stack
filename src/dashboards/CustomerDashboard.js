// src/dashboards/CustomerDashboard.js
import React, { useState, useEffect } from 'react';
import './CustomerDashboard.css';

const CustomerDashboard = ({ user, updateAccount }) => {
  const [isEditing, setIsEditing] = useState(false);  // State to track if editing form is shown
  const [name, setName] = useState(user.name);
  const [email, setEmail] = useState(user.email);
  const [password, setPassword] = useState(user.password);
  const [isSubmitted, setIsSubmitted] = useState(false); // Track form submission

  // Update the local state when the user data is updated (after form submission)
  useEffect(() => {
    setName(user.name);
    setEmail(user.email);
    setPassword(user.password);
  }, [user]);

  // Function to toggle between displaying details and editing the form
  const handleUpdateClick = () => {
    setIsEditing(true);
  };

  // Handle form submission
  const handleSubmit = (e) => {
    e.preventDefault();
    // Update user details
    updateAccount(user.id, { name, email, password });
    // Clear form fields and hide the form after submission
    setIsSubmitted(true);
    setIsEditing(false);
    alert('Account updated successfully!');
  };

  return (
    <div>
      <h1>Customer Dashboard</h1>

      {/* Show the user details if not editing */}
      {!isEditing && (
        <>
          <div>
            <p><strong>Name:</strong> {name}</p>
            <p><strong>Email:</strong> {email}</p>
            <p><strong>Password:</strong> {password}</p>
          </div>
          <button onClick={handleUpdateClick}>Update</button>
        </>
      )}

      {/* Show the form when editing */}
      {isEditing && (
        <form onSubmit={handleSubmit}>
          <div>
            <label>
              Name:
              <input 
                type="text" 
                value={name} 
                onChange={(e) => setName(e.target.value)} 
              />
            </label>
          </div>
          <div>
            <label>
              Email:
              <input 
                type="email" 
                value={email} 
                onChange={(e) => setEmail(e.target.value)} 
              />
            </label>
          </div>
          <div>
            <label>
              Password:
              <input 
                type="password" 
                value={password} 
                onChange={(e) => setPassword(e.target.value)} 
              />
            </label>
          </div>
          <button type="submit">Submit Update</button>
        </form>
      )}

      {/* Show a confirmation message after submission */}
      {isSubmitted && (
        <p>Account updated successfully!</p>
      )}

      {/* View Subscriptions Section */}
      <h2>View Subscriptions</h2>
      {user.subscriptions && user.subscriptions.length > 0 ? (
        <ul>
          {user.subscriptions.map((subscription) => (
            <li key={subscription.id}>
              <strong>{subscription.name}</strong> - Status: {subscription.status}
            </li>
          ))}
        </ul>
      ) : (
        <p>No subscriptions available.</p>
      )}
    </div>
  );
};

export default CustomerDashboard;
