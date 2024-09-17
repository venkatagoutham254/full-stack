// src/dashboards/ViewerAdminDashboard.js
import React from 'react';
import './ViewerAdminDashboard.css';

const ViewerAdminDashboard = ({ producers = [] }) => {
  return (
    <div>
      <h1>Viewer Admin Dashboard</h1>

      {/* View Producers */}
      <h2>View Producers</h2>
      <ul>
        {producers.length > 0 ? (
          producers.map((producer, index) => (
            <li key={index}>
              {producer.producerName} ({producer.email})
            </li>
          ))
        ) : (
          <li>No producers available.</li>
        )}
      </ul>
    </div>
  );
};

export default ViewerAdminDashboard;
