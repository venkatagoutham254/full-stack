// src/components/DivisionForm.js
import React, { useState } from 'react';
import './DivisionForm.css';
const DivisionForm = ({ organizations = [], divisions = [], addDivision }) => {
  const [divisionName, setDivisionName] = useState('');
  const [organizationId, setOrganizationId] = useState(''); // Storing the selected Organization ID

  const handleSubmit = (e) => {
    e.preventDefault();
    const newDivision = { divisionName, organizationId, id: Date.now() }; // Generate unique ID
    addDivision(newDivision);
    setDivisionName('');
    setOrganizationId('');
  };

  return (
    <div>
      <h2>Division Form</h2>
      <form onSubmit={handleSubmit}>
        <label>
          Division Name:
          <input 
            type="text" 
            value={divisionName} 
            onChange={(e) => setDivisionName(e.target.value)} 
          />
        </label>
        
        <label>
          Select Organization:
          <select value={organizationId} onChange={(e) => setOrganizationId(e.target.value)}>
            <option value="">Select an Organization</option>
            {organizations.map((organization) => (
              <option key={organization.id} value={organization.id}>
                {organization.organizationName}
              </option>
            ))}
          </select>
        </label>

        <button type="submit">Submit</button>
      </form>

      <h3>Division List</h3>
      <ul>
        {divisions.length > 0 ? (
          divisions.map((division) => (
            <li key={division.id}>
              {division.divisionName} - Organization ID: {division.organizationId}
            </li>
          ))
        ) : (
          <li>No divisions available.</li>
        )}
      </ul>
    </div>
  );
};

export default DivisionForm;
