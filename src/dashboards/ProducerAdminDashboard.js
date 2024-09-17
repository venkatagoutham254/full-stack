// src/dashboards/ProducerAdminDashboard.js
import React, { useState } from 'react';
import './ProducerAdminDashboard.css';

const ProducerAdminDashboard = ({ producers = [], organizations = [], divisions = [], updateOrganization, updateDivision, deleteOrganization, deleteDivision }) => {
  const [isEditing, setIsEditing] = useState({ organization: null, division: null });
  const [editDetails, setEditDetails] = useState({ name: '' });

  // Edit selected organization or division
  const handleEditClick = (type, item) => {
    setIsEditing({ ...isEditing, [type]: item.id });
    setEditDetails({
      name: item.organizationName || item.divisionName,
    });
  };

  // Update the form fields
  const handleChange = (e) => {
    const { name, value } = e.target;
    setEditDetails((prevDetails) => ({
      ...prevDetails,
      [name]: value,
    }));
  };

  // Submit the updated data for organization or division
  const handleSubmit = (type) => {
    if (type === 'organization' && isEditing.organization) {
      updateOrganization(isEditing.organization, { organizationName: editDetails.name });
    } else if (type === 'division' && isEditing.division) {
      updateDivision(isEditing.division, { divisionName: editDetails.name });
    }
    setIsEditing({ organization: null, division: null });
  };

  return (
    <div>
      <h1>Producer Admin Dashboard</h1>

      {/* View Producers */}
      <h2>View Producers</h2>
      <ul>
        {producers.map((producer) => (
          <li key={producer.id}>
            {producer.producerName} ({producer.email})
          </li>
        ))}
      </ul>

      {/* Manage Organizations */}
      <h2>Manage Organizations</h2>
      <ul>
        {organizations.map((organization) => (
          <li key={organization.id}>
            {isEditing.organization === organization.id ? (
              <form onSubmit={() => handleSubmit('organization')}>
                <input 
                  type="text" 
                  name="name" 
                  value={editDetails.name} 
                  onChange={handleChange} 
                />
                <button type="submit">Save</button>
              </form>
            ) : (
              <>
                {organization.organizationName}
                <button onClick={() => handleEditClick('organization', organization)}>Edit</button>
                <button onClick={() => deleteOrganization(organization.id)}>Delete</button>
              </>
            )}
          </li>
        ))}
      </ul>

      {/* Manage Divisions */}
      <h2>Manage Divisions</h2>
      <ul>
        {divisions.map((division) => (
          <li key={division.id}>
            {isEditing.division === division.id ? (
              <form onSubmit={() => handleSubmit('division')}>
                <input 
                  type="text" 
                  name="name" 
                  value={editDetails.name} 
                  onChange={handleChange} 
                />
                <button type="submit">Save</button>
              </form>
            ) : (
              <>
                {division.divisionName}
                <button onClick={() => handleEditClick('division', division)}>Edit</button>
                <button onClick={() => deleteDivision(division.id)}>Delete</button>
              </>
            )}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default ProducerAdminDashboard;
