// src/pages/Home.js
import React from 'react';

function Home({ producers, organizations, divisions }) {
  return (
    <div className="home">
      <h1>Welcome to User Management</h1>

      <h2>Producers</h2>
      <ul>
        {producers.map((producer) => (
          <li key={producer.producerId}>
            {producer.producerName} ({producer.email}) - Status: {producer.status}
          </li>
        ))}
      </ul>

      <h2>Organizations</h2>
      <ul>
        {organizations.map((organization) => (
          <li key={organization.organizationId}>
            {organization.organizationName} - Producer ID: {organization.producerId}
          </li>
        ))}
      </ul>

      <h2>Divisions</h2>
      <ul>
        {divisions.map((division) => (
          <li key={division.divisionId}>
            {division.divisionName} - Organization ID: {division.organizationId}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default Home;
