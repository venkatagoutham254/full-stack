// src/dashboards/SuperAdminDashboard.js
import React, { useState } from 'react';
import './SuperAdminDashboard.css';
const SuperAdminDashboard = ({ producers = [], users = [], updateProducer, deleteProducer, updateUser, deleteUser }) => {
  const [isEditing, setIsEditing] = useState({ producer: null, user: null });
  const [editDetails, setEditDetails] = useState({ name: '', email: '' });

  // Edit the selected producer or user
  const handleEditClick = (type, item) => {
    setIsEditing({ ...isEditing, [type]: item.id }); // Set the ID of the item being edited
    setEditDetails({
      name: type === 'producer' ? item.producerName : item.name,
      email: item.email,
    });
  };

  // Update the form input fields
  const handleChange = (e) => {
    const { name, value } = e.target;
    setEditDetails((prevDetails) => ({
      ...prevDetails,
      [name]: value,
    }));
  };

  // Submit the updated details for producer or user
  const handleSubmit = (type) => {
    if (type === 'producer' && isEditing.producer) {
      updateProducer(isEditing.producer, { producerName: editDetails.name, email: editDetails.email });
    } else if (type === 'user' && isEditing.user) {
      updateUser(isEditing.user, { name: editDetails.name, email: editDetails.email });
    }
    setIsEditing({ producer: null, user: null }); // Reset after editing
  };

  return (
    <div>
      <h1>Super Admin Dashboard</h1>

      {/* Manage Producers */}
      <h2>Manage Producers</h2>
      <ul>
        {producers.map((producer) => (
          <li key={producer.id}>
            {isEditing.producer === producer.id ? (
              <form onSubmit={() => handleSubmit('producer')}>
                <input 
                  type="text" 
                  name="name" 
                  value={editDetails.name} 
                  onChange={handleChange} 
                />
                <input 
                  type="email" 
                  name="email" 
                  value={editDetails.email} 
                  onChange={handleChange} 
                />
                <button type="submit">Save</button>
              </form>
            ) : (
              <>
                {producer.producerName} ({producer.email})
                <button onClick={() => handleEditClick('producer', producer)}>Edit</button>
                <button onClick={() => deleteProducer(producer.id)}>Delete</button>
              </>
            )}
          </li>
        ))}
      </ul>

      {/* View All Producers */}
      <h2>View All Producers</h2>
      <ul>
        {producers.map((producer) => (
          <li key={producer.id}>
            {producer.producerName} ({producer.email})
          </li>
        ))}
      </ul>

      {/* Manage Users */}
      <h2>Manage Users</h2>
      <ul>
        {users.map((user) => (
          <li key={user.id}>
            {isEditing.user === user.id ? (
              <form onSubmit={() => handleSubmit('user')}>
                <input 
                  type="text" 
                  name="name" 
                  value={editDetails.name} 
                  onChange={handleChange} 
                />
                <input 
                  type="email" 
                  name="email" 
                  value={editDetails.email} 
                  onChange={handleChange} 
                />
                <button type="submit">Save</button>
              </form>
            ) : (
              <>
                {user.name} ({user.email})
                <button onClick={() => handleEditClick('user', user)}>Edit</button>
                <button onClick={() => deleteUser(user.id)}>Delete</button>
              </>
            )}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default SuperAdminDashboard;
