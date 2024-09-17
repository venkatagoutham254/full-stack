import React from 'react';

const DashboardComponent = ({
  producers,
  organizations,
  divisions,
  deleteProducer,
  deleteOrganization,
  deleteDivision,
  editProducer,
  editOrganization,
  editDivision
}) => {
  return (
    <div>
      <h1>Super Admin Dashboard</h1>

      {/* Manage Producers */}
      <h2>Manage Producers</h2>
      <ul>
        {producers.map((producer, index) => (
          <li key={index}>
            {producer.producerName} ({producer.email}) - {producer.status}
            <button onClick={() => editProducer(index, { producerName: "Edited Producer", email: producer.email, status: producer.status })}>Edit</button>
            <button onClick={() => deleteProducer(index)}>Delete</button>
          </li>
        ))}
      </ul>

      {/* Manage Organizations */}
      <h2>Manage Organizations</h2>
      <ul>
        {organizations.map((organization, index) => (
          <li key={index}>
            {organization.organizationName} - Producer ID: {organization.producerId}
            <button onClick={() => editOrganization(index, { organizationName: "Edited Organization", producerId: organization.producerId })}>Edit</button>
            <button onClick={() => deleteOrganization(index)}>Delete</button>
          </li>
        ))}
      </ul>

      {/* Manage Divisions */}
      <h2>Manage Divisions</h2>
      <ul>
        {divisions.map((division, index) => (
          <li key={index}>
            {division.divisionName} - Organization ID: {division.organizationId}
            <button onClick={() => editDivision(index, { divisionName: "Edited Division", organizationId: division.organizationId })}>Edit</button>
            <button onClick={() => deleteDivision(index)}>Delete</button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default DashboardComponent;
