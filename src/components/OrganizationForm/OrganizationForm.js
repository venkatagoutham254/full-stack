// src/components/OrganizationForm.js
import React, { useState } from 'react';
import './OrganizationForm.css';
const OrganizationForm = ({ producers = [], organizations = [], addOrganization }) => {
  const [organizationName, setOrganizationName] = useState('');
  const [producerId, setProducerId] = useState(''); // Storing the selected Producer ID

  const handleSubmit = (e) => {
    e.preventDefault();
    const newOrganization = { organizationName, producerId, id: Date.now() }; // Generate unique ID
    addOrganization(newOrganization);
    setOrganizationName('');
    setProducerId('');
  };

  return (
    <div>
      <h2>Organization Form</h2>
      <form onSubmit={handleSubmit}>
        <label>
          Organization Name:
          <input 
            type="text" 
            value={organizationName} 
            onChange={(e) => setOrganizationName(e.target.value)} 
          />
        </label>
        
        <label>
          Select Producer:
          <select value={producerId} onChange={(e) => setProducerId(e.target.value)}>
            <option value="">Select a Producer</option>
            {producers.map((producer) => (
              <option key={producer.id} value={producer.id}>
                {producer.producerName}
              </option>
            ))}
          </select>
        </label>

        <button type="submit">Submit</button>
      </form>

      <h3>Organization List</h3>
      <ul>
        {organizations.length > 0 ? (
          organizations.map((organization) => (
            <li key={organization.id}>
              {organization.organizationName} - Producer ID: {organization.producerId}
            </li>
          ))
        ) : (
          <li>No organizations available.</li>
        )}
      </ul>
    </div>
  );
};

export default OrganizationForm;
